package pos.machine;

public class detailedItem extends ItemInfo {
    private final int quantity;
    public detailedItem(String barcode, String name, int price, int quantity) {
        super(barcode, name, price);
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
}
