package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Participant {

    protected static final int BLACKJACK_SCORE = 21;

    protected final Name name;
    protected final Cards cards;

    public Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public void append(Card card) {
        cards.append(card);
    }

    protected boolean isBust() {
        return cards.calculateTotalScore() > BLACKJACK_SCORE;
    }

    public abstract boolean isDrawable();

    public abstract GameResult decideResult(Participant participant);

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}
