package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.result.ProfitResult;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class Participants {

    private static final int SPREAD_CARD_SIZE = 2;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(final Deck deck, final List<String> names, final List<BigDecimal> bettingAmounts) {
        Dealer initializedDealer = Dealer.initializeDealer(deck, SPREAD_CARD_SIZE);
        Players initializedPlayers = Players.from(names, bettingAmounts, deck, SPREAD_CARD_SIZE);
        return new Participants(initializedDealer, initializedPlayers);
    }

    public void dealInitialCards(final Deck deck) {
        dealer.dealInitialCards(deck, SPREAD_CARD_SIZE);
        players.dealInitialCards(deck, SPREAD_CARD_SIZE);
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
        dealer.stay();
    }
}
