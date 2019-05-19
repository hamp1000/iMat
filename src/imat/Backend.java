package imat;

import javafx.util.Pair;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.util.*;

public class Backend {

    private static Map<Category, List<Product>> categoryListMap = new HashMap<>();

    private static IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    public static List<Product> searchProducts(String s) {
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

        return (int) Math.round(amount);
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

    private static void addCategoryProductsToList(List<Product> list, ProductCategory category) {
        list.addAll(dataHandler.getProducts(category));
    }

    public static List<Product> getCategoryProducts(Category category) {
        switch (category) {
            case MEAT_FISH: {
                if (categoryListMap.containsKey(Category.MEAT_FISH)) {
                    return categoryListMap.get(Category.MEAT_FISH);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.MEAT);
                addCategoryProductsToList(products, ProductCategory.FISH);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.MEAT_FISH, products);
                return products;
            }

            case FRUIT_BERRIES: {
                if (categoryListMap.containsKey(Category.FRUIT_BERRIES)) {
                    return categoryListMap.get(Category.FRUIT_BERRIES);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.BERRY);
                addCategoryProductsToList(products, ProductCategory.CITRUS_FRUIT);
                addCategoryProductsToList(products, ProductCategory.EXOTIC_FRUIT);
                addCategoryProductsToList(products, ProductCategory.MELONS);
                addCategoryProductsToList(products, ProductCategory.FRUIT);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.FRUIT_BERRIES, products);
                return products;
            }

            case VEGETABLES: {
                if (categoryListMap.containsKey(Category.VEGETABLES)) {
                    return categoryListMap.get(Category.VEGETABLES);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.VEGETABLE_FRUIT);
                addCategoryProductsToList(products, ProductCategory.CABBAGE);
                addCategoryProductsToList(products, ProductCategory.ROOT_VEGETABLE);
                addCategoryProductsToList(products, ProductCategory.POD);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.VEGETABLES, products);
                return products;
            }

            case DRINKS: {
                if (categoryListMap.containsKey(Category.DRINKS)) {
                    return categoryListMap.get(Category.DRINKS);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.HOT_DRINKS);
                addCategoryProductsToList(products, ProductCategory.COLD_DRINKS);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.DRINKS, products);
                return products;
            }

            case DAIRIES: {
                if (categoryListMap.containsKey(Category.DAIRIES)) {
                    return categoryListMap.get(Category.DAIRIES);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.DAIRIES);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.DAIRIES, products);
                return products;
            }

            case BREAD: {
                if (categoryListMap.containsKey(Category.BREAD)) {
                    return categoryListMap.get(Category.BREAD);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.BREAD);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.BREAD, products);
                return products;
            }

            case DRY_STUFFS: {
                if (categoryListMap.containsKey(Category.DRY_STUFFS)) {
                    return categoryListMap.get(Category.DRY_STUFFS);
                }
                List<Product> products = new ArrayList<>();
                addCategoryProductsToList(products, ProductCategory.FLOUR_SUGAR_SALT);
                addCategoryProductsToList(products, ProductCategory.PASTA);
                addCategoryProductsToList(products, ProductCategory.POTATO_RICE);
                addCategoryProductsToList(products, ProductCategory.NUTS_AND_SEEDS);
                addCategoryProductsToList(products, ProductCategory.HERB);
                products.sort(Comparator.comparing(Product::getName));
                categoryListMap.put(Category.DRY_STUFFS, products);
                return products;
            }

            default:
                throw new IllegalArgumentException("Invalid category");
        }
    }


}
