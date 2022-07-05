package mvp.backend.factory;

import mvp.backend.domain.ServerProperty;
import mvp.backend.domain.promo.PromoHeader;
import mvp.backend.domain.promo.PromoSKU;
import mvp.backend.domain.promo.SKU;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactoryPromo {
    public static PromoHeader createPromo(SKU sku, PromoHeader promo){
        if (checkPromo(sku, promo)){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                String insert = "INSERT INTO [PromoPlannerMVP].[dbo].[Promo_Header]" +
                        "  ([IdSKU], [IdPromo], [IdAdd], [DateMin], [DateMax], [IdUser], [IdStatusApproval], Volume, " +
                        "  [Discount], [AddPlacements], [AddExpenses], [ShelfMechanics], [Distribution],[Outlets], " +
                        "  [AddVolume], [ChainVolume])" +
                        "  VALUES (?, " +
                        "  (SELECT top 1 IdPromo " +
                        "  FROM [PromoPlannerMVP].[dbo].[_SPR_Promo] " +
                        "  where [Type of promo] is not null " +
                        "  order by ABS(DATEDIFF(day, [Start date - Shelf], ?)) + " +
                        "  ABS(DATEDIFF(day, [Finish date - Shelf], ?)))" +
                        ", 0, ?, ?, 0, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?); SELECT SCOPE_IDENTITY()";
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setInt(1, sku.getId());
                statement.setDate(2, promo.getDateStart());
                statement.setDate(3, promo.getDateEnd());
                statement.setDate(4, promo.getDateStart());
                statement.setDate(5, promo.getDateEnd());
                statement.setFloat(6, promo.getVolume());
                statement.setFloat(7, promo.getDiscount());
                statement.setInt(8, promo.getAddPlacements());
                statement.setFloat(9, promo.getAddExpenses());
                statement.setInt(10, promo.getShelfMechanics());
                statement.setInt(11, promo.getDistribution());
                statement.setFloat(12, promo.getOutlets());
                statement.setFloat(13, promo.getAddVolume());
                statement.setFloat(14, promo.getChainVolume());

                ResultSet rs = statement.executeQuery();
                if (rs.next()){
                    promo.setId(rs.getInt(1));
                }
                setPeriodFromDirectory(connection, promo.getId());
                PromoHeader promoHeader = getPromoByID(connection, promo.getId());
                connection.commit();
                return promoHeader;
            } catch (SQLException e){
                try{
                    connection.rollback();
                } catch (SQLException exc){
                }
                return null;
            }
        } else {
            return null;
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
    public static PromoHeader updatePromo(SKU sku, PromoHeader promo){
        if (checkPromo(sku, promo)){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                String insert = "UPDATE [PromoPlannerMVP].[dbo].[Promo_Header]" +
                        "  SET [DateMin] = ?, [DateMax] = ?, Volume = ?, Discount = ?," +
                        "  AddPlacements = ?, AddExpenses = ?, ShelfMechanics = ?," +
                        "  Distribution = ?, Outlets = ?, AddVolume = ?, ChainVolume = ?," +
                        "  [IdPromo] = (SELECT top 1 IdPromo " +
                        "  FROM [PromoPlannerMVP].[dbo].[_SPR_Promo] " +
                        "  where [Type of promo] is not null " +
                        "  order by ABS(DATEDIFF(day, [Start date - Shelf], ?)) + " +
                        "  ABS(DATEDIFF(day, [Finish date - Shelf], ?)))" +
                        "  WHERE [IDPromoHeader] = ?";
                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setDate(1, promo.getDateStart());
                statement.setDate(2, promo.getDateEnd());
                statement.setFloat(3, promo.getVolume());
                statement.setFloat(4, promo.getDiscount());
                statement.setInt(5, promo.getAddPlacements());
                statement.setFloat(6, promo.getAddExpenses());
                statement.setInt(7, promo.getShelfMechanics());
                statement.setInt(8, promo.getDistribution());
                statement.setFloat(9, promo.getOutlets());
                statement.setFloat(10, promo.getAddVolume());
                statement.setFloat(11, promo.getChainVolume());
                statement.setDate(12, promo.getDateStart());
                statement.setDate(13, promo.getDateEnd());
                statement.setInt(14, promo.getId());

                statement.executeUpdate();
                setPeriodFromDirectory(connection, promo.getId());
                PromoHeader promoHeader = getPromoByID(connection, promo.getId());
                connection.commit();
                return promoHeader;
            } catch (SQLException e){
                try {
                    connection.rollback();
                }
                catch (SQLException exc){

                }
                return null;
            }
        }
        return null;
    }
    public static boolean deletePromo(int id){
        try {
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String delete = "DELETE FROM [PromoPlannerMVP].[dbo].[Promo_Header]" +
                    "  WHERE [IDPromoHeader] = ?";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, id);

            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
    public static List<PromoSKU> getPromoList(Date dateStart, Date dateEnd){
        List<PromoSKU> promoSKUList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String select = "SELECT s.[IdSKU], [Material_Desc_RUS], h.*, " +
                    "  DATEDIFF(day, h.[DateMin], h.[DateMax]), " +
                    "  DATEDIFF(day, h.[ShippingDateMin], h.[ShippingDateMax])" +
                    "  FROM [PromoPlannerMVP].[dbo].[_SPR_SKU] s" +
                    "  left join (select * from Promo_Header where DateMax > ? AND DateMin < ?) as h on s.IdSKU = h.IdSKU" +
                    "  where EXISTS(SELECT 1 from [dbo].[_SPR_Martrix] m where s.IdSKU = m.IdSKU) AND " +
                    "  [Material_Desc_RUS] is not null";
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
                            rs.getInt(9), rs.getInt(10), rs.getInt(25), rs.getFloat(11),
                            rs.getDate(12), rs.getDate(13), rs.getDate(14), rs.getDate(15),
                            rs.getString(16), rs.getFloat(17), rs.getInt(18), rs.getFloat(19),
                            rs.getInt(20), rs.getInt(21), rs.getFloat(22), rs.getFloat(23),
                            rs.getFloat(24), rs.getInt(26));
                    promoList.add(promoHeader);
                }
            }
        } catch (SQLException e){
            return null;
        }
        return promoSKUList;
    }
    private static boolean setPeriodFromDirectory(Connection connection, int id){
        try {
            String update = "UPDATE ph" +
                    " SET ph.DateMin = p.[Start date - Shelf]," +
                    " ph.DateMax = p.[Finish date - Shelf]," +
                    " ph.[ShippingDateMin] = p.[Start date - Shipment]," +
                    " ph.[ShippingDateMax] = p.[Finish date - Shipment]" +
                    " FROM [PromoPlannerMVP].[dbo].[Promo_Header] ph" +
                    " inner join _SPR_Promo as p on ph.IdPromo = p.IdPromo" +
                    " where ph.IDPromoHeader = ?";
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, id);

            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e){
            return false;
        }
    }
    private static PromoHeader getPromoByID(Connection connection, int id) {
        try {

            String select = "SELECT *, DATEDIFF(day, [DateMin], [DateMax])," +
                    " DATEDIFF(day, [ShippingDateMin], [ShippingDateMax])" +
                    " FROM [PromoPlannerMVP].[dbo].[Promo_Header]" +
                    " WHERE [IDPromoHeader] = ?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            PromoHeader promoHeader = null;
            if (rs.next()){
                Date date = Date.valueOf("2022-01-01");
                promoHeader = new PromoHeader(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),
                        rs.getInt(8), rs.getInt(23), rs.getInt(9), date,
                        date, rs.getDate(12), rs.getDate(13), "",
                        rs.getFloat(15), rs.getInt(16), rs.getFloat(17), rs.getInt(18),
                        rs.getInt(19), rs.getFloat(20), rs.getFloat(21),
                        rs.getFloat(22), rs.getInt(24));
            }
            return promoHeader;
        }
        catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException exc){

            } finally {
                return null;
            }
        }
    }
}
