package blackjack.state;

import blackjack.domain.card.Card;

import java.util.Arrays;

public class StateFactory {

    public static State draw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        if (cards.list().stream().anyMatch(Card::isAce) &&
                cards.list().stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        return new Hit(cards);
    }

    public static State dealerDraw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        if (cards.list().stream().anyMatch(Card::isAce) &&
                cards.list().stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        return new DealerHit(cards);
    }
}
