package imat;

import javafx.util.Pair;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backend {

    private static Map<Category, List<Product>> categoryListMap = new HashMap<>();

    private static IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    
    public static List<Product> searchProduct(String s){
        return dataHandler.findProducts(s);
    }

    public static List<Product> favorites() {
        return dataHandler.favorites();
    }

    public static void addProductToCart(Product product) {
        dataHandler.getShoppingCart().addProduct(product);
    }

    public static void removeProductFromCart(Product product) {
        List<ShoppingItem> items = dataHandler.getShoppingCart().getItems();
        for (int i = 0; i < items.size(); ++i) {
            if (items.get(i).getProduct().getProductId() == product.getProductId()) {
                dataHandler.getShoppingCart().removeItem(i);
                break;
            }
        }
    }

    public static int getProductCartAmount(Product product) {
        List<ShoppingItem> cartItems = dataHandler.getShoppingCart().getItems();

        double amount = 0;
        for (ShoppingItem item : cartItems) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                amount += item.getAmount();
            }
        }

        return (int)Math.round(amount);
    }

    public static List<Pair<Category, String>> getCategories() {
        List<Pair<Category, String>> categories = new ArrayList<>();
        categories.add(new Pair(Category.MEAT_FISH, "Kött & Fisk"));
        categories.add(new Pair(Category.FRUIT_BERRIES, "Frukt & Bär"));
        categories.add(new Pair(Category.VEGETABLES, "Grönsaker"));
        categories.add(new Pair(Category.DRINKS, "Drycker"));
        categories.add(new Pair(Category.DAIRIES, "Mejeri & Ägg"));
        categories.add(new Pair(Category.BREAD, "Bröd"));
        categories.add(new Pair(Category.DRY_STUFFS, "Skafferi"));
        return categories;
    }

    public static List<Product> getCategoryProducts(Category category) {
        switch (category) {
            case MEAT_FISH: {
                if (categoryListMap.containsKey(Category.MEAT_FISH)) {
                    return categoryListMap.get(Category.MEAT_FISH);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.MEAT));
                products.addAll(dataHandler.getProducts(ProductCategory.FISH));
                categoryListMap.put(Category.MEAT_FISH, products);
                return products;
            }

            case FRUIT_BERRIES: {
                if (categoryListMap.containsKey(Category.FRUIT_BERRIES)) {
                    return categoryListMap.get(Category.FRUIT_BERRIES);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.BERRY));
                products.addAll(dataHandler.getProducts(ProductCategory.CITRUS_FRUIT));
                products.addAll(dataHandler.getProducts(ProductCategory.EXOTIC_FRUIT));
                products.addAll(dataHandler.getProducts(ProductCategory.MELONS));
                products.addAll(dataHandler.getProducts(ProductCategory.FRUIT));
                categoryListMap.put(Category.FRUIT_BERRIES, products);
                return products;
            }

            case VEGETABLES: {
                if (categoryListMap.containsKey(Category.VEGETABLES)) {
                    return categoryListMap.get(Category.VEGETABLES);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.VEGETABLE_FRUIT));
                products.addAll(dataHandler.getProducts(ProductCategory.CABBAGE));
                products.addAll(dataHandler.getProducts(ProductCategory.ROOT_VEGETABLE));
                products.addAll(dataHandler.getProducts(ProductCategory.POD));
                categoryListMap.put(Category.VEGETABLES, products);
                return products;
            }

            case DRINKS: {
                if (categoryListMap.containsKey(Category.DRINKS)) {
                    return categoryListMap.get(Category.DRINKS);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.HOT_DRINKS));
                products.addAll(dataHandler.getProducts(ProductCategory.COLD_DRINKS));
                categoryListMap.put(Category.DRINKS, products);
                return products;
            }

            case DAIRIES: {
                if (categoryListMap.containsKey(Category.DAIRIES)) {
                    return categoryListMap.get(Category.DAIRIES);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.DAIRIES));
                categoryListMap.put(Category.DAIRIES, products);
                return products;
            }

            case BREAD: {
                if (categoryListMap.containsKey(Category.BREAD)) {
                    return categoryListMap.get(Category.BREAD);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.BREAD));
                categoryListMap.put(Category.BREAD, products);
                return products;
            }

            case DRY_STUFFS: {
                if (categoryListMap.containsKey(Category.DRY_STUFFS)) {
                    return categoryListMap.get(Category.DRY_STUFFS);
                }
                List<Product> products = new ArrayList<>();
                products.addAll(dataHandler.getProducts(ProductCategory.FLOUR_SUGAR_SALT));
                products.addAll(dataHandler.getProducts(ProductCategory.PASTA));
                products.addAll(dataHandler.getProducts(ProductCategory.POTATO_RICE));
                products.addAll(dataHandler.getProducts(ProductCategory.NUTS_AND_SEEDS));
                products.addAll(dataHandler.getProducts(ProductCategory.HERB));
                categoryListMap.put(Category.DRY_STUFFS, products);
                return products;
            }

            default:
                throw new IllegalArgumentException("Invalid category");
        }
    }


}
