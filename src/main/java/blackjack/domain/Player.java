package blackjack.domain;

public class Player extends Gamer {
    public static final int THRESHOLD_OF_BURST = 21;
    private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";

    private WinnerFlag result;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() < THRESHOLD_OF_BURST;
    }

    @Override
    public Boolean continueDraw(String draw) {
        return isDrawCard(draw);
    }

    private Boolean isDrawCard(String draw) {
        String pattern = "[yn]";
        if (!draw.matches(pattern)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
        }
        return draw.equals("y");
    }

    public void matchResult(WinnerFlag result) {
        this.result = result;
    }

    public WinnerFlag getResult() {
        return result;
    }
}
