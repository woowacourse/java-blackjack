package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private final Name name;

    public Player(Name name, Cards cards) {
        super(cards);
        this.name = name;
    }

    @Override
    public boolean isReceivable() {
        return false;
    }

    @Override
    public int calculateBestScore() {
        List<Card> cards = this.cards.getCards();
        int sum = 0;

        for (Card card : cards) {
            sum += card.getNumber().getScore();
        }

        for (Card card : cards) {
            if (card.isAce() && sum + 10 <= 21) {
                sum += 10;
            }
        }
        return sum;
    }
}
