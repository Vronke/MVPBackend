package mvp.backend.domain.promo;

import java.util.Date;

public class PromoMatrix {
    private int id;
    private int skuId;
    private Date dateMin;
    private Date dateMax;

    public PromoMatrix() {
    }

    public PromoMatrix(int id, int skuId, Date dateMin, Date dateMax) {
        this.id = id;
        this.skuId = skuId;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
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

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }
}
