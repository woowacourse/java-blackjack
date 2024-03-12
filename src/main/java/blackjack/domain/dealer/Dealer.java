package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.Score;
import blackjack.domain.rule.state.Init;
import blackjack.domain.rule.state.State;
import java.util.List;
import java.util.stream.Stream;

public class Dealer {

    public static final int NEED_CARD_NUMBER_MAX = 16;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private State state;

    public Dealer(final Deck deck) {
        this.deck = deck;
        this.state = new Init();
    }

    public boolean needMoreCard() {
        return state.getHands().calculateScore().isSmallerOrEqual(new Score(NEED_CARD_NUMBER_MAX));
    }


    public Card drawCard() {
        return deck.pick();
    }

    public List<List<Card>> drawCards(final int playerSize, final int eachCardCount) {
        return Stream.generate(() -> deck.pick(eachCardCount))
                .limit(playerSize)
                .toList();
    }

    public void draw() {
        state = state.draw(drawCard());
    }

    public void start() {
        state = state.start(drawCard(), drawCard());
    }

    public boolean isNotBlackjack() {
        return !state.getHands().calculateScore().isBlackjack();
    }

    public Hands getOpenedHands() {
        return new Hands(List.of(state.getHands().getFirstCard()));
    }

    public State getState() {
        return state;
    }

    public Hands getHands() {
        return state.getHands();
    }
}
