package view;

import domain.card.Shape;

import java.util.*;

public class ShapeDisplayMatcher {
    private static final Map<Shape, String> messagesMapper = new EnumMap<>(Shape.class);

    static {
        List<String> messages = new ArrayList<>(List.of("하트", "스페이드", "클로버", "다이아몬드"));

        for (Shape shape : Shape.values()) {
            messagesMapper.put(shape, messages.remove(0));
        }
    }
    
    private ShapeDisplayMatcher() {
        throw new IllegalArgumentException("인스턴스를 생성할 수 없는 클래스입니다.");
    }
    
    public static String displayName(Shape shape) {
        return messagesMapper.get(shape);
    }
}
