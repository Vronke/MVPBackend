package mvp.backend.domain.promo;

public class Cannibalization {
    private int id;
    private int skuId;
    private int week;
    private float proportion;
    private int typeOfPromo;

    public Cannibalization() {
    }

    public Cannibalization(int id, int skuId, int week, float proportion, int typeOfPromo) {
        this.id = id;
        this.skuId = skuId;
        this.week = week;
        this.proportion = proportion;
        this.typeOfPromo = typeOfPromo;
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }

    public int getTypeOfPromo() {
        return typeOfPromo;
    }

    public void setTypeOfPromo(int typeOfPromo) {
        this.typeOfPromo = typeOfPromo;
    }
}
