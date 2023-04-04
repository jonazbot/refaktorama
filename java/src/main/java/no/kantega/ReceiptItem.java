package no.kantega;

public class ReceiptItem {
    final Menu menuItem;
    final Discount itemDiscount;
    ReceiptItem(Menu menuItem, Discount itemDiscount) {
        this.menuItem = menuItem;
        this.itemDiscount = itemDiscount;
    }

    float computeItemCost() {
        return Math.round(this.menuItem.getPrice() * this.itemDiscount.getAmount()) ;
    }
}
