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
        GameTable gameTable = new GameTable();
        Dealer dealer = dealer();
        dealer.draw(drawStrategy);
        dealer.draw(drawStrategy);
        gameTable.addParticipant(dealer);

        return gameTable;
    }

    private Dealer dealer () {
        return new Dealer(Constants.DEALER_NAME, emptyhand());
    }

    private Hand emptyhand() {
        return Hand.empty();
    }
}
