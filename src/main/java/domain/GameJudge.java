package domain;

import domain.board.DealerBoard;
import domain.board.PlayerBoard;

public class GameJudge {

    public static final int BLACK_JACK_POINT = 21;

    public static GameResult getPlayerGameResult(DealerBoard dealerBoard, PlayerBoard playerBoard) {
        int dealerPoint = dealerBoard.getPoint();
        int playerPoint = playerBoard.getPoint();
        return judgePlayer(dealerPoint, playerPoint);
    }

    private static GameResult judgePlayer(int dealerPoint, int playerPoint) {
        if (dealerPoint > BLACK_JACK_POINT) {
            return whenDealerIsBust(playerPoint);
        }
        if (dealerPoint < playerPoint) {
            return GameResult.WIN;
        }
        if (dealerPoint > playerPoint) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private static GameResult whenDealerIsBust(int playerPoint) {
        if (playerPoint > BLACK_JACK_POINT) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
