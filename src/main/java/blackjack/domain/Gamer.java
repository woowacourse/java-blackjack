package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public abstract class Gamer {

    private final Name name;
    private final Cards cards;

    public Gamer(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getTotalScore() > BLACKJACK_SCORE;
    }

    abstract GameResult createResult(Gamer gamer);

    abstract boolean canHit();

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }
}
