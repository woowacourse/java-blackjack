package blackjack.domain.participant;

import blackjack.domain.DealerWinningResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Participants {

    private static final int DEALER_COUNT = 1;
    private static final int SPREAD_MULTIPLY_SIZE = 2;
    private static final int SPREAD_SIZE = 1;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

//    public void spreadAllTwoCards(final Hand hand) {
//        dealer.receiveCards(hand.getPartialCards(0, SPREAD_MULTIPLY_SIZE));
//        players.receiveCards(hand.getPartialCards(SPREAD_MULTIPLY_SIZE, hand.getSize()), SPREAD_MULTIPLY_SIZE);
//    }

//    public int getPlayerSize() {
//        return players.getSize();
//    }
//
//    public Entry<String, Hand> showInitialDealerCards() {
//        return Map.entry(dealer.getNickname(), dealer.showInitialCards());
//    }
//
//    public Map<String, Hand> showInitialParticipantsCards() {
//        return players.showTotalInitialCards();
//    }

//    public boolean canDealerHit() {
//        return dealer.canHit();
//    }

//    public boolean canPlayerHit(final int index) {
//        final Gamer player = players.getPlayer(index);
//        return player.canHit();
//    }

//    public Players findHitAvailablePlayers() {
//        return players.findHitAvailablePlayers();
//    }
//
//    public void spreadOneCardToDealer(final Card card) {
//        spreadOneCard(dealer, card);
//    }
//
//    public Entry<String, Hand> showDealerCards() {
//        return Map.entry(dealer.getNickname(), dealer.showAllCards());
//    }
//
//    public Map<String, Hand> showPlayersCards() {
//        return players.showTotalCards();
//    }
//
//    public DealerWinningResult makeDealerWinningResult() {
//        return dealer.makeDealerWinningResult(players.calculateScores());
//    }

//    public int getInitialTotalCardsSize() {
//        return (DEALER_COUNT + players.getSize()) * SPREAD_MULTIPLY_SIZE;
//    }
//
//    private void spreadOneCard(final Gamer gamer, final Card card) {
//        gamer.receiveCards(new Hand(List.of(card)));
//    }

//    public Gamer getDealer() {
//        return dealer;
//    }

//    public List<String> getPlayersNames() {
//        return players.getNames();
//    }

//    public Players getPlayers() {
//        return players;
//    }
}
