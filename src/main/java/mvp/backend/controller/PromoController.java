package mvp.backend.controller;

import mvp.backend.domain.promo.PromoHeader;
import mvp.backend.domain.promo.PromoSKU;
import mvp.backend.domain.promo.SKU;
import mvp.backend.factory.FactoryPromo;
import mvp.backend.factory.FactorySalesIN;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("promo")
public class PromoController {
    @PostMapping("/getPromoList")
//    public List<PromoSKU> getPromoList(@RequestBody Filter[] filter) {
    public List<PromoSKU> getPromoList() {
        return FactoryPromo.getPromoList();
    }

    @PostMapping(value = "/createPromo")
    public  boolean createPromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.createPromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/checkPromo")
    public boolean checkPromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.checkPromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/updatePromo")
    public boolean updatePromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.updatePromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/getFactsSalesInOfWeekBySKU")
    public List<Float> getFactsSalesInOfWeekBySKU(@RequestParam Date dateStart,
                                                  @RequestParam Date dateEnd,
                                                  @RequestParam int skuId){
        return FactorySalesIN.getFactsSalesInOfWeekBySKU(dateStart, dateEnd, skuId);
    }
}
