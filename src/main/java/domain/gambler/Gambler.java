package domain.gambler;

import domain.card.Card;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public abstract class Gambler {

    public static final int MAX_SCORE = 21;
    public static final int INITIAL_CARD_COUNT = 2;

    protected final Cards cards;

    protected Gambler(Cards cards) {
        validateInitialCardsSize(cards);
        this.cards = cards;
    }

    public abstract List<Card> openInitialCards();

    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards());
    }

    public void addOneCard(Card card) {
        cards.addOneCard(card);
    }

    public int sumCardScores() {
        return cards.sumCardScores();
    }

    public boolean isBust() {
        return sumCardScores() > MAX_SCORE;
    }

    public boolean doesNotBust() {
        return !isBust();
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }
}
