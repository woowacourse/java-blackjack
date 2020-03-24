package blackjack.domain.participants;

import static blackjack.domain.rule.BasicRule.*;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public abstract class Participant {
    protected final Hand hand;
    private final Name name;

    public Participant(final String input) {
        this.hand = new Hand();
        this.name = new Name(input);
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    public int score() {
        return Score.validate(hand.calculate());
    }

    public int countHand() {
        return hand.size();
    }

    public boolean isBust() {
        return score() > BUST_LIMIT;
    }

    public boolean isBlackjack() {
        return score() == BUST_LIMIT && countHand() == BLACK_JACK_CARD_SIZE;
    }

    public String handStatus() {
        return hand.toString();
    }

    public String getName() {
        return name.getName();
    }

    public abstract void drawMoreCard(final Deck deck);
}
