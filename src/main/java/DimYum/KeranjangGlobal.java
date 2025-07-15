package DimYum;

import java.util.ArrayList;
import java.util.List;

public class KeranjangGlobal {
    private static KeranjangGlobal instance;
    private final List<Product> productList;

    private KeranjangGlobal() {
        productList = new ArrayList<>();
    }

    public static KeranjangGlobal getInstance() {
        if (instance == null) {
            instance = new KeranjangGlobal();
        }
        return instance;
    }

    public void addProduct(Product p) {
        // Jika sudah ada produk dengan nama sama, update jumlahnya
        for (Product existing : productList) {
            if (existing.getName().equals(p.getName())) {
                existing.setQuantity(existing.getQuantity() + p.getQuantity());
                return;
            }
        }
        productList.add(p);
    }

    public Product[] getProducts() {
        return productList.toArray(new Product[0]);
    }

    public void clearCart() {
        productList.clear();
    }
}
