package blackjack.domain.state;

import blackjack.domain.card.Card;

public class StateFactory {

    public static State draw(Card firstCard, Card secondCard) {
        if (isHit(firstCard, secondCard)) {
            return new Hit();
        }
        return null;
    }

    private static boolean isHit(Card firstCard, Card secondCard) {
        return firstCard.numberScore() + secondCard.numberScore() < 21;
    }
}
