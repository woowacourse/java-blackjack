package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;

public class Dealer extends Participant {

    public static final int MAX_ACE_NUMBER = 11;
    public static final int MAX_RECEIVABLE_SCORE = 17;

    public Dealer(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() < MAX_RECEIVABLE_SCORE;
    }

    @Override
    public int calculateBestScore() {
        return this.cards.getCards().stream()
                .map(Card::getNumber)
                .map(number -> {
                    if (number.getScore() == Number.ACE.getScore()) {
                        return MAX_ACE_NUMBER;
                    }
                    return number.getScore();
                })
                .reduce(0, Integer::sum);
    }
}
