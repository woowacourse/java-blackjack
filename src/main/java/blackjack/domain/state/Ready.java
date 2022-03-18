package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Ready implements State {

    protected PlayerCards cards;

    public static State deal(Card card1, Card card2) {
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        PlayerCards playerCards = new PlayerCards(cards);
        if (playerCards.isBlackjack()) {
            return new Blackjack(playerCards);
        }
        return new Hit(playerCards);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.get());
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract boolean isFinished();
}
