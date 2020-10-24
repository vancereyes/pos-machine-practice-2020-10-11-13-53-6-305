package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PosMachine {


    public String printReceipt(List<String> barcodes) {

        List<ItemInfo> itemInfos = getItemsFromDB();
        List<Integer> quantities = getItemQuantities(itemInfos, barcodes);
        String receipt = printItemDetails(itemInfos, quantities);
        return receipt;

    }

    private String printItemDetails(List<ItemInfo> itemInfos, List<Integer> quantities) {

        List<Integer> subtotals = new ArrayList<>();
        String receipt = "***<store earning no money>Receipt***\n";

        for (int a = 0; a < itemInfos.size(); a++) {
            int tempSubTotal = getSubtotal(itemInfos.get(a).getPrice(), quantities.get(a));
            if (tempSubTotal > 0) {
                receipt = receipt + String.format("Name: %s, ", itemInfos.get(a).getName());
                receipt += String.format("Quantity: %s, ", quantities.get(a));
                receipt += String.format("Unit price: %d (yuan), ", itemInfos.get(a).getPrice());
                receipt += String.format("Subtotal: %d (yuan)\n", tempSubTotal);
                subtotals.add(tempSubTotal);
            }
        }
        if (subtotals.size() > 0) {
            receipt += "----------------------\n";
            receipt += "Total: " + getTotal(subtotals) + " (yuan)\n";
            receipt += "**********************";
        }
        if (receipt == "***<store earning no money>Receipt***\\n") return null;
        else return receipt;
    }


    private int getSubtotal(int price, int quantity) {
        return price * quantity;
    }

    private List<Integer> getItemQuantities(List<ItemInfo> itemInfos, List<String> barcodes) {

        List<Integer> quantities = new ArrayList<Integer>();
        for (ItemInfo itemInfo : itemInfos) {
            quantities.add(Collections.frequency(barcodes, itemInfo.getBarcode()));
        }

        return quantities;
    }

    private List<ItemInfo> getItemsFromDB() {
        return ItemDataLoader.loadAllItemInfos();
    }

    private int getTotal(List<Integer> subtotal) {

        return subtotal.stream().reduce(0, Integer::sum);
    }


}
