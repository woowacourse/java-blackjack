package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import java.util.List;

public class Player extends Participant {

    public static final int ACE_ADDITIONAL_NUMBER = 10;
    public static final int BEST_SCORE = 21;

    private final Name name;

    public Player(Name name, Cards cards) {
        super(cards);
        this.name = name;
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() <= BEST_SCORE;
    }

    @Override
    public int calculateBestScore() {
        List<Card> cards = this.cards.getCards();

        int sum = cards.stream()
                .map(Card::getNumber)
                .map(Number::getScore)
                .reduce(0, Integer::sum);

        for (Card card : cards) {
            sum = getBest(sum, card);
        }

        return sum;
    }

    private int getBest(int sum, Card card) {
        if (card.isAce() && sum + ACE_ADDITIONAL_NUMBER <= BEST_SCORE) {
            sum += ACE_ADDITIONAL_NUMBER;
        }
        return sum;
    }
}
