package dto;

public class CardDto {

    private final String letter;
    private final String name;

    public CardDto(String letter, String name) {
        this.letter = letter;
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public String getName() {
        return name;
    }
}
