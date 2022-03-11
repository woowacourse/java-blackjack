package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected static final int MAX_SCORE = 21;

    private final Cards cards;
    private final String name;

    Player(final List<Card> cards, final String name) {
        validateCards(cards);
        validateName(name);
        this.cards = new Cards(cards);
        this.name = name;
    }

    private void validateCards(final List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 비어있는 카드입니다.");
        }
    }

    private void validateName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    public int calculateFinalScore() {
        final int score = getScoreByAceEleven();

        if (score <= MAX_SCORE) {
            return score;
        }
        return getScoreByAceOne();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }

    protected int getScoreByAceOne() {
        return cards.calculateScoreByAceOne();
    }

    protected int getScoreByAceEleven() {
        return cards.calculateScoreByAceEleven();
    }
}
