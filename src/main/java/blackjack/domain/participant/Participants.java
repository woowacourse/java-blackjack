package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.DealerWinningResult;
import java.util.List;
import java.util.Map;

public final class Participants {

    private static final int DEALER_SIZE = 1;
    private static final int SPREAD_CARD_SIZE = 2;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(final Players players) {
        Dealer dealer = Dealer.createEmpty();
        return new Participants(dealer, players);
    }

    public int getSize() {
        return DEALER_SIZE + players.getSize();
    }

    public void receiveInitialCards(final Hand hand) {
        dealer.receiveCards(hand.getPartialCards(0, SPREAD_CARD_SIZE));
        players.receiveCardsByCount(hand.getPartialCards(SPREAD_CARD_SIZE, hand.getSize()), SPREAD_CARD_SIZE);
    }

    public Players findHitAvailablePlayers() {
        return players.findHitAvailablePlayers();
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public String getDealerName() {
        return dealer.getNickname();
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

    public DealerWinningResult makeWinningResult() {
        return dealer.makeDealerWinningResult(players.calculateScores());
    }
}
