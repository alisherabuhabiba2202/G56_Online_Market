package uz.pdp.g56_online_market.mappers;

import uz.pdp.g56_online_market.daos.BrandDAO;
import uz.pdp.g56_online_market.dtos.BrandDTO;
import uz.pdp.g56_online_market.entities.Brands;

public class BrandMapper {
   public static BrandDTO brandToBrandDTO(Brands brand) {
       return new BrandDTO().builder()
               .Id(brand.getId())
               .name(brand.getName())
               .filepath(brand.getFilepath())
               .build();
   }
}
