package blackjack.domain.user;

import blackjack.domain.Rule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {

    protected final List<Card> cards;
    protected final Name name;
    protected Score score;

    protected User(Name name) {
        this.cards = new ArrayList<>();
        this.name = name;
        this.score = new Score();
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
        // score = rule.sumPoint(cards);
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean isWinTo(User other) {
        return this.score.isGreaterThan(other.score);
    }
}
