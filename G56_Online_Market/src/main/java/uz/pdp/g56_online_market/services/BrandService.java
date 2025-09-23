package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.BrandDAO;
import uz.pdp.g56_online_market.dtos.BrandDTO;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.entities.Products;

import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private final BrandDAO brandDAO = new BrandDAO();

    public List<BrandDTO> getBrandsByPageable(int page, int size){
        List<Brands> brands = brandDAO.getBrandsByPageable(page,size);
        List<BrandDTO> brandDTOs = new ArrayList<>();
        for (Brands brand : brands) {
            brandDTOs.add(new BrandDTO().builder()
                            .Id(brand.getId())
                            .name(brand.getName())
                            .filepath(brand.getFilepath())
                             .build());
        }
        brandDTOs.forEach(item->{
            item.setQuantity(brandDAO.getBrandQuantityById(item.getId()));
        });
            return brandDTOs;
    }
}
