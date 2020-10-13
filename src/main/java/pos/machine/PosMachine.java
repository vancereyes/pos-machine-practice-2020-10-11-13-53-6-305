package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {

        List detailedItem = convertToItems(barcodes);
        return null;
    }

    private List convertToItems(List<String> barcodes) {

        List<detailedItem> detailedItems  = new ArrayList<>();
        List<String> quantity = new ArrayList<>();
        quantity = barcodes.stream()
                .distinct()
                .collect(Collectors.toList());

        for (String barcode : barcodes ){
            ItemInfo itemInfo = ItemDataLoader.loadAllItemInfos().stream()
                    .filter(i -> i.getBarcode().equals(barcode))
                    .findFirst().get();

            detailedItems.add(new detailedItem(barcode, itemInfo.getName(), itemInfo.getPrice(), Collections.frequency(barcodes,barcode)));
        }
    return detailedItems;
    }


}
