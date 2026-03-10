package dto;

import java.util.List;

public record PlayerResult(String name, List<Card> hand, int score) {
    public PlayerResult {
        hand = List.copyOf(hand);
    }
}
