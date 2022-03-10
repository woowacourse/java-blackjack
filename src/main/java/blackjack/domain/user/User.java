package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.behavior.CardShowBehavior;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.strategy.HitStrategy;

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

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
        this.score = Score.addUpPointToScore(cards);
    }

    public List<Card> showCards() {
        return List.copyOf(cards);
    }

    public String getName() {
        return name.getName();
    }

    public Score getScore() {
        return score;
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean isWinTo(User other) {
        return this.score.isGreaterThan(other.score);
    }

    public abstract List<Card> showInitCards();

    public void hitOrStay(Deck deck, HitStrategy hitStrategy) {
        if (hitStrategy.isHit()) {
            drawCard(deck);
        }
    }

}
