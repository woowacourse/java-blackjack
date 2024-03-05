package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public enum Shape {
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private static final Map<Integer, Shape> shapeByOrder = new HashMap<>();

    private final String value;

    static {
        for (Shape cardShape : Shape.values()) {
            shapeByOrder.put(shapeByOrder.size(), cardShape);
        }
    }

    Shape(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Shape getByOrder(int order) {
        if (!shapeByOrder.containsKey(order)) {
            throw new NullPointerException("존재하지 않는 카드 모양입니다.");
        }
        return shapeByOrder.get(order);
    }
}
