package domain.service;

import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.model.Players;
import domain.vo.Result;
import java.util.Map;

public class BlackJackGame {

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;

    public BlackJackGame(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
    }

    public void giveCard(final Participant participant) {
        cardDistributor.giveCard(participant);
    }

    public void giveInitCards(final Participant participant) {
        cardDistributor.giveInitCards(participant);
    }

    public void giveInitCards(final Players players) {
        cardDistributor.giveInitCards(players);
    }

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final Players players) {
        return blackJackResultMaker.makePlayersResult(dealer, players);
    }

    public Result makeDealerResult(final Dealer dealer, final Players players) {
        return blackJackResultMaker.makeDealerResult(dealer, players);
    }
}
