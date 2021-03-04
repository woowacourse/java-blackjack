package blackjack.domain;

public class Player extends Gamer {
    public static final int THRESHOLD_OF_BURST = 21;
    private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
    private static final String AGREE = "y";
    private static final String DISAGREE = "n";

    private WinnerFlag result;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.makeJudgingPoint() < THRESHOLD_OF_BURST;
    }

    @Override
    public Boolean continueDraw(String draw) {
        if(draw.equals(AGREE)){
            return true;
        }
        if(draw.equals(DISAGREE)){
            return false;
        }
        throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
    }

    public void matchResult(WinnerFlag result) {
        this.result = result;
    }

    public WinnerFlag getResult() {
        return result;
    }
}
