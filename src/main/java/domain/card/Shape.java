package domain.card;

public enum Shape {
    SPADE,
    HEART,
    DIAMOND,
    CLUB;

    public String formatCardShape() {
        if (this == DIAMOND) {
            return "다이아몬드";
        }
        if (this == HEART) {
            return "하트";
        }
        if (this == CLUB) {
            return "클로버";
        }
        if (this == SPADE) {
            return "스페이드";
        }
        return "";
    }
}
