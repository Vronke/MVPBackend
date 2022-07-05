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
    private int shippingDays;
    private float volume;
    private Date orderDateStart;
    private Date orderDateEnd;
    private Date shippingDateStart;
    private Date shippingDateEnd;
    private String approving;
    private Float discount;
    private int addPlacements;
    private Float addExpenses;
    private int shelfMechanics;
    private int distribution;
    private Float outlets;
    private Float addVolume;
    private Float chainVolume;

    public PromoHeader() {
    }

    public PromoHeader(int id, int skuId, int promoId, int addressId, Date dateStart, Date dateEnd, int userId,
                       int statusApprovalId, int days, float volume, Date orderDateStart, Date orderDateEnd,
                       Date shippingDateStart, Date shippingDateEnd, String approving, Float discount,
                       int addPlacements, Float addExpenses, int shelfMechanics, int distribution,
                       Float outlets, Float addVolume, Float chainVolume, int shippingDays) {
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
        this.orderDateStart = orderDateStart;
        this.orderDateEnd = orderDateEnd;
        this.shippingDateStart = shippingDateStart;
        this.shippingDateEnd = shippingDateEnd;
        this.approving = approving;
        this.discount = discount;
        this.addPlacements = addPlacements;
        this.addExpenses = addExpenses;
        this.shelfMechanics = shelfMechanics;
        this.distribution = distribution;
        this.outlets = outlets;
        this.addVolume = addVolume;
        this.chainVolume = chainVolume;
        this.shippingDays = shippingDays;
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

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }

    public Date getShippingDateStart() {
        return shippingDateStart;
    }

    public void setShippingDateStart(Date shippingDateStart) {
        this.shippingDateStart = shippingDateStart;
    }

    public Date getShippingDateEnd() {
        return shippingDateEnd;
    }

    public void setShippingDateEnd(Date shippingDateEnd) {
        this.shippingDateEnd = shippingDateEnd;
    }

    public String getApproving() {
        return approving;
    }

    public void setApproving(String approving) {
        this.approving = approving;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public int getAddPlacements() {
        return addPlacements;
    }

    public void setAddPlacements(int addPlacements) {
        this.addPlacements = addPlacements;
    }

    public Float getAddExpenses() {
        return addExpenses;
    }

    public void setAddExpenses(Float addExpenses) {
        this.addExpenses = addExpenses;
    }

    public int getShelfMechanics() {
        return shelfMechanics;
    }

    public void setShelfMechanics(int shelfMechanics) {
        this.shelfMechanics = shelfMechanics;
    }

    public int getDistribution() {
        return distribution;
    }

    public void setDistribution(int distribution) {
        this.distribution = distribution;
    }

    public Float getOutlets() {
        return outlets;
    }

    public void setOutlets(Float outlets) {
        this.outlets = outlets;
    }

    public Float getAddVolume() {
        return addVolume;
    }

    public void setAddVolume(Float addVolume) {
        this.addVolume = addVolume;
    }

    public Float getChainVolume() {
        return chainVolume;
    }

    public void setChainVolume(Float chainVolume) {
        this.chainVolume = chainVolume;
    }

    public int getShippingDays() {
        return shippingDays;
    }

    public void setShippingDays(int shippingDays) {
        this.shippingDays = shippingDays;
    }
}
