package domain;

public class GameJudge {

    public static final int BLACK_JACK_POINT = 21;

    public static GameResult judgePlayerWithDealerPointAndPlayerPoint(int dealerPoint, int playerPoint) {
        return judgePlayer(dealerPoint, playerPoint);
    }

    private static GameResult judgePlayer(int dealerPoint, int playerPoint) {
        if (dealerPoint > BLACK_JACK_POINT) {
            return whenDealerIsBust(playerPoint);
        }
        if (playerPoint > BLACK_JACK_POINT) {
            return whenPlayerIsBust(dealerPoint);
        }
        return whenBothNotBust(dealerPoint, playerPoint);
    }

    private static GameResult whenDealerIsBust(int playerPoint) {
        if (playerPoint > BLACK_JACK_POINT) {
            return GameResult.DRAW;
        }
        return GameResult.WIN;
    }

    private static GameResult whenPlayerIsBust(int dealerPoint) {
        if (dealerPoint > BLACK_JACK_POINT) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }

    private static GameResult whenBothNotBust(int dealerPoint, int playerPoint) {
        if (dealerPoint < playerPoint) {
            return GameResult.WIN;
        }
        if (dealerPoint > playerPoint) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
