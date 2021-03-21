package blackjack.controller.dto;

public class CardDto {

    private final String pattern;
    private final String number;

    public CardDto(String pattern, String number) {
        this.pattern = pattern;
        this.number = number;
    }

    public String getPattern() {
        return pattern;
    }

    public String getNumber() {
        return number;
    }
}
