package mvp.backend.factory;

import mvp.backend.domain.ServerProperty;
import mvp.backend.domain.promo.PromoHeader;
import mvp.backend.domain.promo.PromoSKU;
import mvp.backend.domain.promo.SKU;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactoryPromo {
    public static int createPromo(SKU sku, PromoHeader promo){
        if (checkPromo(sku, promo)){
            try {
                Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                String insert = "INSERT INTO [PromoPlannerMVP].[dbo].[Promo_Header]" +
                        "  ([IdSKU], [IdPromo], [IdAdd], [DateMin], [DateMax], [IdUser], [IdStatusApproval], Volume)" +
                        "  VALUES (?, 0, 0, ?, ?, 0, 0, ?); SELECT SCOPE_IDENTITY()";
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setInt(1, sku.getId());
                statement.setDate(2, promo.getDateStart());
                statement.setDate(3, promo.getDateEnd());
                statement.setFloat(4, promo.getVolume());
                ResultSet rs = statement.executeQuery();
                connection.commit();
                if (rs.next()){
                    return rs.getInt(1);
                }
                return -1;
            } catch (SQLException e){
                return -1;
            }
        } else {
            return -1;
        }
    }
    public static boolean checkPromo(SKU sku, PromoHeader promo){
        try{
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String select = "SELECT TOP 1 1" +
                    " FROM [PromoPlannerMVP].[dbo].[Promo_Header]" +
                    " WHERE [IDPromoHeader] != ? AND IdSKU = ? AND (" +
                    " (? >= [DateMin] AND ? <= [DateMax]) OR " +
                    " (? >= [DateMin] AND ? <= [DateMax]) OR " +
                    " (? <= [DateMin] AND ? >= [DateMax]))";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, promo.getId());
            statement.setInt(2, sku.getId());
            statement.setDate(3, promo.getDateStart());
            statement.setDate(4, promo.getDateStart());
            statement.setDate(5, promo.getDateEnd());
            statement.setDate(6, promo.getDateEnd());
            statement.setDate(7, promo.getDateStart());
            statement.setDate(8, promo.getDateEnd());
            ResultSet rs = statement.executeQuery();
            return !rs.next();
        } catch (SQLException e){
            return false;
        }
    }
    public static boolean updatePromo(SKU sku, PromoHeader promo){
        if (checkPromo(sku, promo)){
            try {
                Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                String insert = "UPDATE [PromoPlannerMVP].[dbo].[Promo_Header]" +
                        "  SET [DateMin] = ?, [DateMax] = ?, Volume = ?" +
                        "  WHERE [IDPromoHeader] = ?";
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setDate(1, promo.getDateStart());
                statement.setDate(2, promo.getDateEnd());
                statement.setFloat(3, promo.getVolume());
                statement.setInt(4, promo.getId());
                statement.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException e){
                return false;
            }
        }
        return true;
    }
    public static List<PromoSKU> getPromoList(Date dateStart, Date dateEnd){
        List<PromoSKU> promoSKUList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String select = "SELECT s.[IdSKU], [Material_Desc_RUS], h.*, DATEDIFF(day, h.[DateMin], h.[DateMax]) + 1" +
                    "  FROM [PromoPlannerMVP].[dbo].[_SPR_SKU] s" +
                    "  left join (select * from Promo_Header where DateMax > ? AND DateMin < ?) as h on s.IdSKU = h.IdSKU" +
                    "  where EXISTS(SELECT 1 from [dbo].[_SPR_Martrix] m where s.IdSKU = m.IdSKU) AND " +
                    "  [Material_Desc_RUS] is not null"; //TODO
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setDate(1, dateStart);
            statement.setDate(2, dateEnd);
            ResultSet rs = statement.executeQuery();
            List<PromoHeader> promoList = new ArrayList<>();
            SKU sku = new SKU();
            while (rs.next()){
                if (sku.getId() != rs.getInt(1) && promoList.size() > 0){
                    PromoHeader[] promoArray = new PromoHeader[promoList.size()];
                    promoList.toArray(promoArray);

                    PromoSKU promoSKU = new PromoSKU(sku, promoArray);

                    promoSKUList.add(promoSKU);
                    promoList = new ArrayList<>();
                }
                sku = new SKU(rs.getInt(1), rs.getString(2));
                int checkNull = rs.getInt(3);
                if (rs.wasNull()){
                    PromoSKU promoSKU = new PromoSKU(sku);
                    promoSKUList.add(promoSKU);
                } else {
                    PromoHeader promoHeader = new PromoHeader(rs.getInt(3), rs.getInt(4),
                            rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getDate(8),
                            rs.getInt(9), rs.getInt(10), rs.getInt(12), rs.getFloat(11));
                    promoList.add(promoHeader);
                }
            }
        } catch (SQLException e){
            return null;
        }
        return promoSKUList;
    }
}
