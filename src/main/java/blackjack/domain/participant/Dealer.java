package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    void init(List<Card> rawCards) {
        for (Card rawCard : rawCards) {
            cards.add(rawCard);
        }
    }

    public CardCount drawCards(CardFactory cardFactory) {
        int count = 0;
        while (getStatus() == Status.HIT && getScore() <= 16) {
            hit(cardFactory);
            count++;
        }

        return CardCount.of(count);
    }

    public Card openCard() {
        return cards.findFirst();
    }

    public String getName() {
        return "딜러";
    }
}
