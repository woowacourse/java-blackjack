package blackjack.player;

import blackjack.card.Card;
import blackjack.game.BlackJackGame;
import java.util.List;

public class Participant extends Player {

    public Participant(String name) {
        super(name);
    }

    Participant(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean hasDrawableScore() {
        return hand.calculateScore() < BlackJackGame.BLACKJACK_MAX_SCORE;
    }

    @Override
    public List<Card> revealCardsOnFirstPhase() {
        return List.of(hand.getFirstCard(), hand.getSecondCard());
    }
}
