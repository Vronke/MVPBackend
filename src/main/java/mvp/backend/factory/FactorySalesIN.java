package mvp.backend.factory;

import mvp.backend.domain.ServerProperty;
import mvp.backend.domain.promo.PromoHeader;
import mvp.backend.domain.promo.PromoSKU;
import mvp.backend.domain.promo.SKU;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactorySalesIN {
    public static List<Float> getFactsSalesInOfWeekBySKU(Date dateStart, Date dateEnd, int skuId) {
        try {
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String select = "select SUM([Sell-in Tonnes]) * 1000" +
                    "  from _SPR_Date_Week w\n" +
                    "  left join _SPR_Date_Day as d on d.IdWeek = w.IdWeek" +
                    "  left join _SPR_Date_Year as y on y.IdYear = d.idYear" +
                    "  left join (SELECT IdDate, [Sell-in Tonnes] FROM [FactSalesIN] WHERE IdSKU = ?) as f on d.IdDay = f.IdDate" +
                    "    where d.Day >= ? AND d.Day <= ?" +
                    "  group by y.Year, w.Week" +
                    "  order by y.Year, w.Week";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, skuId);
            statement.setDate(2, dateStart);
            statement.setDate(3, dateEnd);
            ResultSet rs = statement.executeQuery();
            List<Float> salesIn = new ArrayList<>();
            while (rs.next()) {
                salesIn.add(rs.getFloat(1));
            }
            return salesIn;
        } catch (SQLException e) {
            return null;
        }
    }
    public static Float getFactsSalesInByPeriodAndSKU(Date dateStart, Date dateEnd, int skuId) {
        try {
            Connection connection = DriverManager.getConnection(ServerProperty.CONNECTION_STRING);
            String select = "select SUM([Sell-in Tonnes]) * 1000" +
                    "  from _SPR_Date_Week w" +
                    "  inner join _SPR_Date_Day as d on d.IdWeek = w.IdWeek" +
                    "  inner join _SPR_Date_Year as y on y.IdYear = d.idYear" +
                    "  inner join [FactSalesIN] as f on d.IdDay = f.IdDate" +
                    "  where IdSKU = ? AND d.Day>= ? AND d.Day <= ?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, skuId);
            statement.setDate(2, dateStart);
            statement.setDate(3, dateEnd);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
            return 0F;
        } catch (SQLException e) {
            return 0F;
        }
    }
}
