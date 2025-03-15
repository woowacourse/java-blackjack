package domain.game;

public enum Winning {
    BLACKJACK("블랙잭"),
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    Winning(String name) {
        this.name = name;
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
}
