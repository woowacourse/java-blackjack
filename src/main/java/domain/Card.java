package domain;

public class Card {
    private final CardValue value;
    private final CardKind kind;


    public Card(String value, String kind) {
        this.value = CardValue.of(value);
        this.kind = CardKind.of(kind);
    }

    public int getValue() {
        return value.getNumber();
    }

    // value + string 합친 값 내보내는 메서드
}
