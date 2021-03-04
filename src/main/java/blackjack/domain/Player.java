package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Player extends Gamer {

    private static final int HIGHEST_POINT = 21;
    private static final String COUPLER = "카드: ";
    private static final String GET_CARD = "y";
    private static final String ERROR_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
    private static final String PATTERN_Y_OR_N = "[yn]";
    private String result;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() < HIGHEST_POINT;
    }

    public Boolean continueDraw(Deck deck) {
        OutputView.noticeGetMoreCard(getName());
        String draw = InputView.isContinueDraw();
        if (isDrawCard(draw)) {
            receiveCard(deck.dealCard());
            OutputView.printPlayerInfo(getInfo());
            return true;
        }
        return false;
    }

    private Boolean isDrawCard(String draw) {
        if (!draw.matches(PATTERN_Y_OR_N)) {
            throw new IllegalArgumentException(ERROR_Y_OR_N);
        }
        return draw.equals(GET_CARD);
    }

    public void matchResult(String result) {
        this.result = result;
    }

    @Override
    public String getInfo() {
        return getName() + COUPLER + getCards();
    }

    public String getResult() {
        return result;
    }
}
