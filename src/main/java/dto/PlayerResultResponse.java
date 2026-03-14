package dto;

import java.util.List;
import model.Card;

public record PlayerResultResponse(String name, List<Card> hand, int score) {
    public PlayerResultResponse {
        hand = List.copyOf(hand);
    }
}
