package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerNameHandResponse {

    private final String name;
    private final List<CardDTO> hand;

    public PlayerNameHandResponse(String name, List<CardDTO> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
    }

    public String getName() {
        return name;
    }

    public List<CardDTO> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
