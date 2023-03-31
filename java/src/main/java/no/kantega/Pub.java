package no.kantega;

import no.kantega.menu.Menu;

import java.util.Map;

public class Pub {
    private static final float STUDENT_DISCOUNT = 0.9F;

    public float computeCost(Map<Menu, Integer> items, boolean student) {
        float price = 0F;
        for (var item : items.keySet()) {
            if (item.getType().equals(Menu.Type.SPIRIT) && items.get(item) > 2) // TODO: Ask client for drink limit clarification
                throw new RuntimeException("Too many items, max 2.");
            if (student && item.getType().equals(Menu.Type.BEER))
                price += Math.round(item.getPrice() * STUDENT_DISCOUNT) * items.get(item);
            else price += item.getPrice() * items.get(item);
        }
        return price;
    }
}
