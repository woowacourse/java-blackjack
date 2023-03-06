package domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Shape {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

   private final String value;

    Shape(String value) {
        this.value = value;
    }

//    public List<String> getShapes() {
//        return Arrays.stream(values())
//                .map(shape->shape.value)
//                .collect(Collectors.toUnmodifiableList());
//    }

    public String getValue() {
        return value;
    }
}
