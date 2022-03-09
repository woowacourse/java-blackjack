package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import java.util.LinkedHashSet;
import java.util.List;

public class Dealer {


    private final Cards cards;

    public Dealer() {
        this.cards = new Cards(new LinkedHashSet<>());
    }

    public void init(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
        cards.add(cardFactory.drawCard());
    }

    void init(List<Card> rawCards) {
        for (Card rawCard : rawCards) {
            cards.add(rawCard);
        }
    }


    public Status getStatus() {
        return cards.getStatus();
    }

    public void drawCards(CardFactory cardFactory) {
        while (getStatus() == Status.NOT_BUST && getScore() <= 16) {
            hit(cardFactory);
        }
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawCard());
    }
}
