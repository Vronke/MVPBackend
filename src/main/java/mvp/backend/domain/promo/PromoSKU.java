package mvp.backend.domain.promo;

public class PromoSKU {
    private SKU sku;
    private PromoHeader[] promoList;

    public PromoSKU() {
    }

    public PromoSKU(SKU sku) {
        this.promoList = new PromoHeader[0];
        this.sku = sku;
    }

    public PromoSKU(SKU sku, PromoHeader[] promoList) {
        this.sku = sku;
        this.promoList = promoList;
    }

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    public PromoHeader[] getPromoList() {
        return promoList;
    }

    public void setPromoList(PromoHeader[] promoList) {
        this.promoList = promoList;
    }
}
