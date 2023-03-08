package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerNameHandScoreResponse {

    private final String name;
    private final List<CardDTO> hand;
    private final int score;

    public PlayerNameHandScoreResponse(String name, List<CardDTO> hand, int score) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<CardDTO> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getScore() {
        return score;
    }
}
