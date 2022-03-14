package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Participant extends Player {

    public Participant(final List<Card> cards, final String name) {
        super(cards, name);
    }

    @Override
    public boolean acceptableCard() {
<<<<<<< HEAD
        return getScoreByAceOne() > MAX_SCORE;
=======
        return getScoreByAceOne() <= MAX_SCORE;
>>>>>>> step1
    }
}
