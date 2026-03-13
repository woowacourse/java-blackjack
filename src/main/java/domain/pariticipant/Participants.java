package domain.pariticipant;


import domain.card.Deck;
import domain.card.Hand;
import domain.result.DealerMatchResult;
import domain.result.MatchCase;
import domain.result.MatchResult;
import domain.result.PlayersMatchResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static constant.BlackjackConstant.DEALER_NAME;
import static domain.result.MatchCase.WIN;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(Players players) {
        this.dealer = new Dealer(new Name(DEALER_NAME), new Hand(new ArrayList<>()));
        this.players = players;
    }

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public void drawInitialCards(Deck deck) {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    // participants -> players -> player
    public PlayersMatchResult calculatePlayersMatchResult() {
        return players.calculateMatchResult(dealer);
    }

}
