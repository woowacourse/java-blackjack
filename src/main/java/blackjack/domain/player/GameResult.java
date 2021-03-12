package blackjack.domain.player;

public enum GameResult {

    DRAW("무승부", 0),
    WIN("승리", 1),
    LOSE("패배", -1);

    private final String name;
    private final double earningRate;

    GameResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public String getName() {
        return name;
    }

    public double earningRate(){
        return earningRate;
    }

    public static GameResult findOppositeGameResult(GameResult gameResult){
        if(gameResult == GameResult.WIN)
            return GameResult.LOSE;
        if(gameResult == GameResult.LOSE)
            return GameResult.WIN;
        return GameResult.DRAW;
    }

}
