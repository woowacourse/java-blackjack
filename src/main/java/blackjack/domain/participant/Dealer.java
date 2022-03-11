package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardCount;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import java.util.List;

public class Dealer extends Participant {

    private static final int SCORE_LOWER_BOUND = 17;

    public Dealer() {
        super();
    }

    void init(List<Card> rawCards) {
        for (Card rawCard : rawCards) {
            getCards().add(rawCard);
        }
    }

    public CardCount drawCards(CardFactory cardFactory) {
        int count = 0;
        while (getStatus() == Status.HIT && getScore() < SCORE_LOWER_BOUND) {
            hit(cardFactory);
            count++;
        }

        return CardCount.of(count);
    }

    public Card openCard() {
        return getCards().findFirst();
    }

    public String getName() {
        return "딜러";
    }
}
