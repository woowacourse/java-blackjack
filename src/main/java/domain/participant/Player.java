package domain.participant;

import domain.card.Card;
import domain.result.WinOrLose;
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

    public WinOrLose compareAtDealerBlackJack() {
        if (this.isBlackJack()) {
            return WinOrLose.DRAW;
        }
        return WinOrLose.LOSE;
    }

    public WinOrLose compareAtFinal(Dealer dealer) {
        if (isBust()) {
            return WinOrLose.LOSE;
        }
        if (this.isBlackJack() || dealer.isBust()) {
            return WinOrLose.WIN;
        }
        return judgeVersus(dealer.calculateBestScore());
    }

    private WinOrLose judgeVersus(int otherScore) {
        int playerScore = calculateBestScore();
        if (playerScore > otherScore) {
            return WinOrLose.WIN;
        }
        if (playerScore < otherScore) {
            return WinOrLose.LOSE;
        }
        return WinOrLose.DRAW;
    }
}
