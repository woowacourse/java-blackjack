package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_CARDS_SIZE = 2;
    protected static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final Cards cards;

    protected Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public void append(Card card) {
        cards.append(card);
    }

    public boolean isBlackjack() {
        return getCards().size() == BLACKJACK_CARDS_SIZE && cards.calculateTotalScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return cards.calculateTotalScore() > BLACKJACK_SCORE;
    }

    public abstract boolean canHit();

    public abstract boolean isWin(Participant participant);

    public abstract boolean isSameScore(Participant participant);

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
