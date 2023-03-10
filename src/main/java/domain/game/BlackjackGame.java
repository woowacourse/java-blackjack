package domain.game;

import domain.card.Card;

public class BlackjackGame {
    public static Hit start(Card card1, Card card2) {
        return new Hit(card1, card2);
    }
}
