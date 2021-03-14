package blackjack.state;

import blackjack.domain.card.Card;

import java.util.Arrays;

public class StateFactory {

    public static State initialPlayerDraw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        if (cards.list().stream().anyMatch(Card::isAce) &&
                cards.list().stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        return new PlayerHit(cards);
    }

    public static State initialDealerDraw(Card firstCard, Card secondCard) {
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        if (cards.list().stream().anyMatch(Card::isAce) &&
                cards.list().stream().anyMatch(Card::isTen)) {
            return new BlackJack(cards);
        }

        if (cards.isOverDrawScore()){
            return new Stay(cards);
        }

        return new DealerHit(cards);
    }
}
