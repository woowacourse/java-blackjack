//package blackjack.domain;
//
//import blackjack.domain.card.Hand;
//import blackjack.domain.participant.Dealer;
//import blackjack.domain.participant.Gamer;
//import blackjack.domain.participant.Players;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class BlackjackGame {
//
//    private final Dealer dealer;
//    private final Players players;
//
//    public BlackjackGame(final Dealer dealer, final Players players) {
//        this.dealer = dealer;
//        this.players = players;
//    }
//
//    public void spreadInitialCards(final Hand hand) {
//        int cardsCount = (DEALER_COUNT + players.getSize()) * SPREAD_MULTIPLY_SIZE;
//        participants.spreadAllTwoCards(hand);
//    }
//
//    public boolean canPlayerHit(final int index) {
//        return participants.canPlayerHit(index);
//    }
//
//    public boolean canDealerHit() {
//        return participants.canDealerHit();
//    }
//
//    public void spreadOneCardToPlayer(final Gamer gamer) {
//        final Hand hand = deck.spreadCards(SPREAD_SIZE);
//        gamer.receiveCards(new Hand(List.of(hand.getFirstCard())));
//    }
//
//    public void spreadOneCardToDealer() {
//        final Hand hand = deck.spreadCards(SPREAD_SIZE);
//        participants.spreadOneCardToDealer(hand.getFirstCard());
//    }
//
//    public int calculateScore(final Gamer gamer) {
//        return gamer.calculateScore();
//    }
//
//    public DealerWinningResult makeDealerWinningResult() {
//        return participants.makeDealerWinningResult();
//    }
//
//    public Entry<String, Hand> showInitialDealerCard() {
//        return participants.showInitialDealerCards();
//    }
//
//    public Map<String, Hand> showInitialPlayersCards() {
//        return participants.showInitialParticipantsCards();
//    }
//
//    public Entry<String, Hand> showDealerCard() {
//        return participants.showDealerCards();
//    }
//
//    public Map<String, Hand> showPlayersCards() {
//        return participants.showPlayersCards();
//    }
//
//    public Players findExtraCardsAvailablePlayers() {
//        return participants.findHitAvailablePlayers();
//    }
//
//    public Gamer getPlayer(final int index) {
//        return participants.getPlayers().getPlayer(index);
//    }
//
//    public List<String> getPlayersNames() {
//        return participants.getPlayersNames();
//    }
//}
