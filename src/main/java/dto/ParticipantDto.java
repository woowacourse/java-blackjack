package dto;

import java.util.List;

public class ParticipantDto {
    private final String name;
    private final List<String> cardNames;
    private final int totalSum;

    public ParticipantDto(String name, List<String> cardNames, int totalSum) {
        this.name = name;
        this.cardNames = cardNames;
        this.totalSum = totalSum;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cardNames;
    }

    public int getTotalSum() {
        return totalSum;
    }
}
