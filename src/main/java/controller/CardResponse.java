package controller;

public class CardResponse {

    private final String name;
    private final String shape;

    public CardResponse(final String name, final String shape) {
        this.name = name;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public String getShape() {
        return shape;
    }
}
