package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerNameHandScoreDto {

    private final String name;
    private final List<CardDto> hand;
    private final int score;

    public PlayerNameHandScoreDto(String name, List<CardDto> hand, int score) {
        this.name = name;
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }
}
