package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;
import blackjack.domain.state.PlayerState;

import static blackjack.domain.game.WinnerFlag.*;

public class Player extends Gamer {
    public static final int THRESHOLD_OF_BURST = 21;
    private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
    private static final String AGREE = "y";
    private static final String DISAGREE = "n";

    private WinnerFlag result;
    private PlayerState playerState;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.makeJudgingPoint() < THRESHOLD_OF_BURST;
    }

    @Override
    public boolean continueDraw(String draw, Deck deck) {
        if (AGREE.equals(draw)) {
            return true;
        }
        if (DISAGREE.equals(draw)) {
            return false;
        }
        throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
    }

    public WinnerFlag calculateResult(Dealer dealer) {
        int playerPoints = makeFinalPoint();
        int dealerPoints = dealer.makeFinalPoint();
        if (dealer.isBurst(dealerPoints) && isBurst(playerPoints) || dealer.isSameThan(playerPoints)) {
            matchResult(DRAW);
        }
        if (isBurst(playerPoints) || (!dealer.isBurst(dealerPoints) && dealer.isBiggerThan(playerPoints))) {
            matchResult(LOSE);
        }
        if ((dealer.isSmallerThan(playerPoints) && !isBurst(playerPoints)) || dealer.isBurst(dealerPoints)) {
            matchResult(WIN);
        }
        return result;
    }

    public void matchResult(WinnerFlag result) {
        this.result = result;
    }

    public WinnerFlag getResult() {
        return result;
    }
}
