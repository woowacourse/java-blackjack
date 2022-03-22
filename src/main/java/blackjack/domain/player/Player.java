package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Player {

    private static final int DIFFERENCE_IN_ACE_SCORE = 10;
    private static final int BLACK_JACK_TARGET_SCORE = 21;

    private final Name name;
    private final Cards cards;

    public Player(final String name) {
        this.name = new Name(name);
        this.cards = new Cards(new ArrayList<>());
    }

    abstract public boolean canAddCard();

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public int getTotal() {
        int totalScore = cards.getTotalScore();

        for (int i = 0; i < cards.countAce(); i++) {
            totalScore = changeAceScore(totalScore);
        }

        return totalScore;
    }

    private int changeAceScore(int totalScore) {
        if (totalScore > BLACK_JACK_TARGET_SCORE) {
            totalScore -= DIFFERENCE_IN_ACE_SCORE;
        }
        return totalScore;
    }

    public boolean isBlackJack() {
        return getTotal() == BLACK_JACK_TARGET_SCORE && cards.hasTwoCards();
    }

    public boolean isBust() {
        return getTotal() > BLACK_JACK_TARGET_SCORE;
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }
}
