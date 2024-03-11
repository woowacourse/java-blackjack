package view.viewer.card;

import domain.card.Shape;

public class ShapeViewer {
    static String show(Shape shape) {
        return switch (shape) {
            case SPADE -> "스페이드";
            case DIAMOND -> "다이아몬드";
            case HEART -> "하트";
            case CLOVER -> "클로버";
        };
    }
}
