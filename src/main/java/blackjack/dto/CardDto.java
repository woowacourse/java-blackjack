package blackjack.dto;

public class CardDto {
    private final String shape;
    private final String number;

    public CardDto(String shape, String number) {
        this.shape = shape;
        this.number = number;
    }

    public String getCard() {
        return number + shape;
    }
}
