package blackjack.domain.participant.state;

import java.util.Collections;

import blackjack.domain.card.Deck;

public class InitialState extends PlayState {

    private static final int DEFAULT_BETTING_AMOUNT = 0;

    private InitialState() {
        super(Collections.emptyList(), DEFAULT_BETTING_AMOUNT);
    }

    public static PlayState initializeState(final Deck deck) {
        final PlayState state = new InitialState();
        return state.drawCard(deck);
    }

    @Override
    public PlayState drawCard(final Deck deck) {
        initiallyDrawCards(deck);
        return new WaitState(this);
    }

    private void initiallyDrawCards(final Deck deck) {
        final int initiallyDrawCardCount = 2;
        for (int i = 0; i < initiallyDrawCardCount; i++) {
            addCard(deck);
        }
    }

    @Override
    public PlayState betAmount(int amount) {
        throw new IllegalStateException("베팅할 준비가 되지 않았습니다.");
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return false;
    }

    @Override
    public String toString() {
        return "InitialState{}";
    }

}
