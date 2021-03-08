package blackjack.domain;

import static blackjack.domain.Cards.HIGHEST_POINT;
import static blackjack.domain.Players.DRAW;
import static blackjack.domain.Players.LOSE;
import static blackjack.domain.Players.RESULT_DRAW;
import static blackjack.domain.Players.RESULT_LOSE;
import static blackjack.domain.Players.RESULT_WIN;
import static blackjack.domain.Players.WIN;

import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class Player extends Gamer {


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
        return this.getPoint() < HIGHEST_POINT;
    }

    public Boolean continueDraw(Deck deck) {
        OutputView.noticeGetMoreCard(getName());
        String draw = InputView.isContinueDraw();
        if (isDrawCard(draw)) {
            receiveCard(deck.dealCard());
            OutputView.printPlayerInfo(this);
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

    public void recordMatchResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void judgeVictory(Map<String, Integer> dealerHistory, int dealerPoint) {
        int playerPoint = getPoint();

        if (isDraw(playerPoint, dealerPoint)) {
            inputMatchResult(dealerHistory, DRAW);
            recordMatchResult(RESULT_DRAW);
        } else if (isWin(playerPoint, dealerPoint)) {
            recordMatchResult(RESULT_WIN);
            inputMatchResult(dealerHistory, LOSE);
        } else if (idLose(playerPoint, dealerPoint)) {
            recordMatchResult(RESULT_LOSE);
            inputMatchResult(dealerHistory, WIN);
        }
    }

    private void inputMatchResult(Map<String, Integer> dealerHistory, String result) {
        dealerHistory.put(result, dealerHistory.get(result) + 1);
    }

    private boolean idLose(int playerPoint, int dealerPoint) {
        return (playerPoint > cards.HIGHEST_POINT) || (playerPoint < dealerPoint);
    }

    private boolean isWin(int playerPoint, int dealerValue) {
        return (dealerValue > cards.HIGHEST_POINT) || (playerPoint > dealerValue);
    }

    private boolean isDraw(int playerPoint, int dealerValue) {
        return (playerPoint > cards.HIGHEST_POINT && dealerValue > cards.HIGHEST_POINT) || (
            playerPoint == dealerValue);
    }
}
