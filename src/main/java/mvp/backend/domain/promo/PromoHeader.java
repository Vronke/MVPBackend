package mvp.backend.domain.promo;

import java.sql.Date;

public class PromoHeader {
    private int id;
    private int skuId;
    private int promoId;
    private int addressId;
    private Date dateStart;
    private Date dateEnd;
    private int userId;
    private int statusApprovalId;

    private int days;
    private float volume;

    public PromoHeader() {
    }

    public PromoHeader(int id, int skuId, int promoId, int addressId, Date dateStart, Date dateEnd, int userId, int statusApprovalId, int days, float volume) {
        this.id = id;
        this.skuId = skuId;
        this.promoId = promoId;
        this.addressId = addressId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.userId = userId;
        this.statusApprovalId = statusApprovalId;
        this.days = days;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusApprovalId() {
        return statusApprovalId;
    }

    public void setStatusApprovalId(int statusApprovalId) {
        this.statusApprovalId = statusApprovalId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
