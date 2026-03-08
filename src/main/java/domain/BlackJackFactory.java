package domain;

import java.util.List;
// 굳이인 느낌
public class BlackJackFactory {

    private final DrawStrategy drawStrategy;

    private BlackJackFactory(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public static BlackJackFactory basedOn(DrawStrategy drawStrategy) {
        return new BlackJackFactory(drawStrategy);
    }

    public GameTable openGame(List<String> playerNames) {
        return GameTable.setupGame(playerNames, drawStrategy);
    }
}
