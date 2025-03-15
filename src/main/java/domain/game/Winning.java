package domain.game;

public enum Winning {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    ;

    private final String name;
    private final double earningRate;

    Winning(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public static Winning determine(ScoreInfo playerScoreInfo, ScoreInfo dealerScoreInfo) {
        if(playerScoreInfo.isBlackJack() && dealerScoreInfo.isBlackJack()) {
            return DRAW;
        }
        if(playerScoreInfo.isBlackJack()) {
            return BLACKJACK;
        }
        if(dealerScoreInfo.isBlackJack()) {
            return LOSE;
        }

        if(playerScoreInfo.isBust()) {
            return LOSE;
        }
        if(dealerScoreInfo.isBust()) {
            return WIN;
        }

        return getWinningByScore(playerScoreInfo.getScore(), dealerScoreInfo.getScore());
    }

    private static Winning getWinningByScore(int playerScore, int dealerScore) {
        if(playerScore > dealerScore) {
            return WIN;
        }
        if(playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
