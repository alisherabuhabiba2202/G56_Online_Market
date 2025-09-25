package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.BrandDAO;
import uz.pdp.g56_online_market.entities.Brands;

import java.util.List;

public class BrandService {
    BrandDAO brandDAO = new BrandDAO();

    public List<Brands> getAllBrands() {
         return brandDAO.getAllBrands();
    }

    public Brands getBrandById(int id) {
        return brandDAO.getBrandById(id);
    }


}
