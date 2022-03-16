package domain.participant;

import domain.card.Card;
import domain.result.Versus;
import java.util.List;

public class Player extends Participant {

    public Player(Name name, List<Card> hand) {
        super(name, hand);
    }

    @Override
    public boolean isNeedToDraw() {
        return !isBlackJack() && !isUpperBoundScore() && !isBust();
    }

    public boolean isNameMatch(Name name) {
        return this.getName().equals(name);
    }

    public Versus compareAtDealerBlackJack() {
        if (this.isBlackJack()) {
            return Versus.DRAW;
        }
        return Versus.LOSE;
    }

    public Versus compareAtFinal(Dealer dealer) {
        if (isBust()) {
            return Versus.LOSE;
        }
        if (this.isBlackJack() || dealer.isBust()) {
            return Versus.WIN;
        }
        return judgeVersus(dealer.calculateBestScore());
    }

    private Versus judgeVersus(int otherScore) {
        int playerScore = calculateBestScore();
        if (playerScore > otherScore) {
            return Versus.WIN;
        }
        if (playerScore < otherScore) {
            return Versus.LOSE;
        }
        return Versus.DRAW;
    }
}
