package dto;

import java.util.List;

public class DealerInfoDto {
    private final String name = "딜러";
    private final List<String> cards;

    public DealerInfoDto(List<String> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
