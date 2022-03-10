package blackjack.domain.user;

import blackjack.domain.Rule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    private static final int BLACKJACK_NUMBER = 21;

    protected final List<Card> cards;
    protected final Name name;
    protected int score = 0;

    protected User(Name name) {
        this.cards = new ArrayList<>();
        this.name = name;
    }

    protected User(String input) {
        this(new Name(input));
    }

    public void drawInitCards(Deck deck) {
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public abstract List<Card> showInitCards();

    public abstract boolean isDrawable();

    public String getName(){
        return name.getName();
    }

    public void calculate(Rule rule) {
        score = rule.sumPoint(cards);
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
