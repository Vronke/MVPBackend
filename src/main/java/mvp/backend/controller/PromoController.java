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
    public List<PromoSKU> getPromoList(@RequestParam Date dateStart,
                                       @RequestParam Date dateEnd) {
        return FactoryPromo.getPromoList(dateStart, dateEnd);
    }

    @PostMapping(value = "/createPromo")
    public PromoHeader createPromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.createPromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/checkPromo")
    public boolean checkPromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.checkPromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/updatePromo")
    public PromoHeader updatePromo(@RequestBody PromoSKU promoSku) {
        return FactoryPromo.updatePromo(promoSku.getSku(), promoSku.getPromoList()[0]);
    }

    @PostMapping("/deletePromo")
    public boolean deletePromo(@RequestParam int id) {
        return FactoryPromo.deletePromo(id);
    }

    @PostMapping("/getFactsSalesInOfWeekBySKU")
    public List<Float> getFactsSalesInOfWeekBySKU(@RequestParam Date dateStart,
                                                  @RequestParam Date dateEnd,
                                                  @RequestParam int skuId){
        return FactorySalesIN.getFactsSalesInOfWeekBySKU(dateStart, dateEnd, skuId);
    }

    @PostMapping("/getFactsSalesInByPeriodAndSKU")
    public Float getFactsSalesInByPeriodAndSKU(@RequestParam Date dateStart,
                                                  @RequestParam Date dateEnd,
                                                  @RequestParam int skuId){
        return FactorySalesIN.getFactsSalesInByPeriodAndSKU(dateStart, dateEnd, skuId);
    }
}
