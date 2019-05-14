package imat;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;


import java.util.List;

public class iMatBackEnd {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ProductCategory category;

    String[] categoryList = {"Pod","Bread","Berry", "Citrus fruit", "Hot drinks", "Cold drinks", "Exotic fruit", "Fish",
            "Vegetable fruit", "Cabbage", "Meat", "Dairies", "Melons", "Flour Sugar Salt", "Nut and Seeds", "Pasta"};

    String[] getCategoryList(){

        return categoryList;

    }

}
