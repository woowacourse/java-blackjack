package blackjack.domain.dto;

public class CardDto {
    private final String shape;
    private final String value;

    public CardDto(String shape, String value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape;
    }

    public String getValue() {
        return value;
    }

}
