package domain.blackjackgame;

import java.util.List;

public record BlackjackResult(String name, List<TrumpCard> cardHands, int cardSum) {
}
