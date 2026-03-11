package domain;

import java.util.List;
// 굳이 둬야 할까?
public class BlackJackFactory {

    private final DrawStrategy drawStrategy;

    private BlackJackFactory(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public static BlackJackFactory basedOn(DrawStrategy drawStrategy) {
        return new BlackJackFactory(drawStrategy);
    }

//    public GameTable openGame(List<String> playerNames) {
//        return GameTable.setupGame(playerNames, drawStrategy);
//    }

    public Participants onlyDealer() {
        return Participants.onlyDealer(drawStrategy);
    }
}
