package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.List;

public abstract class User {

    private static final int BLACKJACK_NUMBER = 21;

    protected final Cards cards;
    protected final String name;
    protected int score = 0;

    protected User(String name) {
        cards = new Cards();
        this.name = name;
    }

    public void drawInitCards(Deck deck) {
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
    }

    public List<Card> showCards() {
        return cards.showCards();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public abstract List<Card> showInitCards();

    public abstract boolean isDrawable();

    public String getName(){
        return name;
    }

    public void calculate() {
        score = cards.sumPoint();
        validateNegative(score);
    }

    public int getScore() {
        return score;
    }

    private void validateNegative(int score) {
        if (score < 0) {
            throw new RuntimeException("점수 계산에 문제가 있습니다.");
        }
    }

    public boolean isBust() {
        return score > BLACKJACK_NUMBER;
    }
}
