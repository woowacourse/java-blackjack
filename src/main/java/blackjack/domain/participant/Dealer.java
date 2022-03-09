package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import java.util.List;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    void init(List<Card> rawCards) {
        for (Card rawCard : rawCards) {
            cards.add(rawCard);
        }
    }

    public void drawCards(CardFactory cardFactory) {
        while (getStatus() == Status.HIT && getScore() <= 16) {
            hit(cardFactory);
        }
    }

    public Card openCard() {
        return super.cards.findFirst();
    }
}
