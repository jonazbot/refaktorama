package no.kantega;

import java.util.ArrayList;
import java.util.List;

public class Pub {
    private final List<ReceiptItem> customerReceipt;

    public Pub() {
        this.customerReceipt = new ArrayList<>();
    }

    public void order(Menu item, int numOfItems, Discount discount) {
        if (item.getType().equals(Menu.Type.SPIRIT) && numOfItems > 2) // TODO: Ask client for drink limit clarification
            throw new RuntimeException("Too many items, max 2.");
        if (discount.isValidForItem(item)) {
            for (int n = 0; n < numOfItems; ++n) {
                customerReceipt.add(new ReceiptItem(item, discount));
            }
        }
        else throw new RuntimeException(item + " does not qualify for " + discount.getName());
    }

    float computeBill() {
        float total = 0F;
        for (var item : this.customerReceipt) {
            total += item.computeItemCost();
        }
        return total;
    }

    public float addTips(float total, float tips) {
        return total + tips;
    }
}
