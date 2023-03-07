package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerNameHandScoreResponse {

    private final String name;
    private final List<Card> hand;
    private final int score;

    public PlayerNameHandScoreResponse(String name, List<Card> hand, int score) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getScore() {
        return score;
    }
}
