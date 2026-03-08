package domain;

import common.Constants;

public class BlackJackFactory {

    private final DrawStrategy drawStrategy;

    private BlackJackFactory(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public static BlackJackFactory basedOn(DrawStrategy drawStrategy) {
        return new BlackJackFactory(drawStrategy);
    }

    public GameTable openGame() {
        return new GameTable();
    }

    private Dealer dealer () {
        return new Dealer(Constants.DEALER_NAME, emptyhand());
    }

    private Hand emptyhand() {
        return Hand.empty();
    }
}
