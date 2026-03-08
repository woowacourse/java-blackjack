package domain.factory;

import common.Constants;
import domain.Dealer;
import domain.DrawStrategy;
import domain.GameTable;
import domain.Hand;
import java.util.ArrayList;

public class BlackJackFactory {

    private final DrawStrategy drawStrategy;

    private BlackJackFactory(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public static BlackJackFactory basedOn(DrawStrategy drawStrategy) {
        return new BlackJackFactory(drawStrategy);
    }

    public GameTable openGameTable () {
        GameTable gameTable = new GameTable();
        Dealer dealer = dealer();
        dealer.draw();
        dealer.draw();
        gameTable.addParticipant(dealer);

        return gameTable;
    }

    private Dealer dealer () {
        return new Dealer(Constants.DEALER_NAME, emptyhand());
    }

    private Hand emptyhand() {
        return new Hand(drawStrategy, new ArrayList<>());
    }
}
