package blackjack.view.dto;

public class CardDto {

    private final String name;

    public CardDto(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
