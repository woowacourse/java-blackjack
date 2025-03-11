package domain.gambler;

import domain.Card;
import domain.Cards;
import java.util.ArrayList;
import java.util.List;

public class Gambler {

    public static final int MAX_SCORE = 21;
    public static final int INITIAL_CARD_COUNT = 2;

    private final Cards cards;

    protected Gambler(Cards cards) {
        validateInitialCardsSize(cards);
        this.cards = cards;
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

    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards());
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }
}
