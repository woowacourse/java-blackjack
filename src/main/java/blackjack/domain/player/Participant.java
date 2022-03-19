package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public final class Participant extends Player {

    public Participant(final List<Card> cards, final String name, Bet bet) {
        super(cards, name, bet);
    }

    @Override
    public boolean acceptableCard() {
        return getScoreByAceOne() <= MAX_SCORE;
    }
}
