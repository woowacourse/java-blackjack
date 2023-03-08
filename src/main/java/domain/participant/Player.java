package domain.participant;

import domain.BlackJackResult;
import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private static final int BURST_NUMBER = 21;

    public Player(final Name name, final DrawnCards drawnCards) {
        super(name, drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        return new ArrayList<>(drawnCards.getCards());
    }

    @Override
    public boolean isDrawable() {
        return calculateScore() < BURST_NUMBER;
    }

    public boolean isWin(Dealer dealer) {
        if (isBurst()) {
            return false;
        }

        if (dealer.isBurst()) {
            return true;
        }

        return this.calculateScore() > dealer.calculateScore();
    }

    public BlackJackResult getResult(Dealer dealer) {
        if (isBlackJack()) {
            return calculateBlackJackCase(dealer);
        }

        if (isBurst()) {
            return BlackJackResult.BURST;
        }


        if (this.calculateScore() > dealer.calculateScore() || dealer.isBurst()) {
            return BlackJackResult.WIN;
        }

        return BlackJackResult.LOSE;
    }

    private static BlackJackResult calculateBlackJackCase(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return BlackJackResult.EACH_BLACKJACK;
        }
        return BlackJackResult.BLACKJACK;
    }
}
