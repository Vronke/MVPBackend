package mvp.backend.domain.promo;

import java.sql.Date;

public class SprPromo {
    private int id;
    private String format;
    private String nameOfPromo;
    private int typeOfPromoId;
    private String description;
    private Date startShelfDate;
    private Date endShelfDate;
    private Date startShipmentDate;
    private Date endShipmentDate;
    private int days;
    private int shippingDays;

    public SprPromo() {
    }

    public SprPromo(int id, String format, String nameOfPromo, int typeOfPromoId, String description,
                    Date startShelfDate, Date endShelfDate, Date startShipmentDate, Date endShipmentDate,
                    int days, int shippingDays) {
        this.id = id;
        this.format = format;
        this.nameOfPromo = nameOfPromo;
        this.typeOfPromoId = typeOfPromoId;
        this.description = description;
        this.startShelfDate = startShelfDate;
        this.endShelfDate = endShelfDate;
        this.startShipmentDate = startShipmentDate;
        this.endShipmentDate = endShipmentDate;
        this.days = days;
        this.shippingDays = shippingDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNameOfPromo() {
        return nameOfPromo;
    }

    public void setNameOfPromo(String nameOfPromo) {
        this.nameOfPromo = nameOfPromo;
    }

    public int getTypeOfPromoId() {
        return typeOfPromoId;
    }

    public void setTypeOfPromoId(int typeOfPromoId) {
        this.typeOfPromoId = typeOfPromoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartShelfDate() {
        return startShelfDate;
    }

    public void setStartShelfDate(Date startShelfDate) {
        this.startShelfDate = startShelfDate;
    }

    public Date getEndShelfDate() {
        return endShelfDate;
    }

    public void setEndShelfDate(Date endShelfDate) {
        this.endShelfDate = endShelfDate;
    }

    public Date getStartShipmentDate() {
        return startShipmentDate;
    }

    public void setStartShipmentDate(Date startShipmentDate) {
        this.startShipmentDate = startShipmentDate;
    }

    public Date getEndShipmentDate() {
        return endShipmentDate;
    }

    public void setEndShipmentDate(Date endShipmentDate) {
        this.endShipmentDate = endShipmentDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(int shippingDays) {
        this.shippingDays = shippingDays;
    }
}
