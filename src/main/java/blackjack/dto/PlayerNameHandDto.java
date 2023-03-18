package blackjack.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerNameHandDto {

    private final String name;
    private final List<CardDto> hand;

    public PlayerNameHandDto(String name, List<CardDto> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getHand() {
        return hand;
    }
}
