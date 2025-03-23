package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.result.ProfitResult;
import java.util.List;
import java.util.Map;

public final class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(final Players players) {
        return new Participants(new Dealer(), players);
    }

    public void dealInitialCards(final Deck deck) {
        dealer.dealInitialCards(players, deck);
    }

    public Players findHitEligiblePlayers() {
        return players.findHitAvailablePlayers();
    }

    public Hand showInitialDealerCards() {
        return dealer.showInitialCards();
    }

    public Map<String, Hand> showInitialPlayersCards() {
        return players.showTotalInitialCards();
    }

    public boolean canDealerHit() {
        return dealer.canHit();
    }

    public void receiveCardToDealer(final Hand hand) {
        dealer.receiveCards(hand);
    }

    public Hand showAllDealerCard() {
        return dealer.showAllCards();
    }

    public Map<String, Hand> showAllPlayersCard() {
        return players.showTotalCards();
    }

    public ProfitResult makeDealerWinningResult() {
        return dealer.calculateProfit(players);
    }

    public String getDealerName() {
        return dealer.getNickname();
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public void stayToDealerIfRunning() {
        dealer.stayIfRunning();
    }
}
