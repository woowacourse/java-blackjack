package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerNameHandResponse {

    private final String name;
    private final List<Card> hand;

    public PlayerNameHandResponse(String name, List<Card> hand) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
