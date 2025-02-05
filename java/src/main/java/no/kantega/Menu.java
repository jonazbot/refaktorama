package no.kantega;

public enum Menu {
    RUM("Rum", Type.INGREDIENT, 65),
    GRENADINE("Grenadine", Type.INGREDIENT, 10),
    LIME_JUICE("Lime Juice", Type.INGREDIENT,10),
    GREEN_STUFF("Green Stuff", Type.INGREDIENT,10),
    TONIC_WATER("Tonic Water", Type.INGREDIENT,20),
    HANSA("Hansa", Type.BEER_OR_CIDER, 74),
    GRANS("Grans", Type.BEER_OR_CIDER, 103),
    STRONGBROW("Strongbow", Type.BEER_OR_CIDER, 110),
    GIN("Gin", Type.SPIRIT, 85),
    GT("GT", Type.SPIRIT, GIN.price + TONIC_WATER.price + GREEN_STUFF.price),
    BACARDI_SPECIAL("Bacardi Special", Type.SPIRIT, GIN.getPrice()/2 + RUM.price + GRENADINE.price + LIME_JUICE.price);

    private final String itemName;
    private final Type type;
    private final int price;

    Menu(String name, Type type, int price) {
        this.itemName = name;
        this.type = type;
        this.price = price;
    }

    public Type getType() { return this.type; }

    public int getPrice() { return this.price; }

    public enum Type {
        BEER_OR_CIDER,
        SPIRIT,
        INGREDIENT
    }
}
