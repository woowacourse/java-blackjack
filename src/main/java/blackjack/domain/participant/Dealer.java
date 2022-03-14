package blackjack.domain.participant;

import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public class Dealer extends Participant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super();
    }

    public DrawCount drawCards(CardDeck cardDeck) {
        int count = 0;
        while (getStatus() == PlayStatus.HIT && getScore() <= HIT_CONDITION) {
            hit(cardDeck.drawCard());
            count++;
        }

        return DrawCount.of(count);
    }

    public Card openCard() {
        return cards.findFirst();
    }

    public String getName() {
        return NAME;
    }
}
