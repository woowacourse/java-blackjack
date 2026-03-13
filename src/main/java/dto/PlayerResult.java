package dto;

import java.util.List;
import model.Card;

public record PlayerResult(String name, List<Card> hand, int score) {
    public PlayerResult {
        hand = List.copyOf(hand);
    }
}
