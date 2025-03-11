package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Collections;
import java.util.List;

public class Participants {

    private static final int DEALER_COUNT = 1;
    private static final int SPREAD_MULTIPLY_SIZE = 2;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void spreadAllTwoCards(final Cards cards) {
        dealer.receiveCards(cards.getPartialCards(0, SPREAD_MULTIPLY_SIZE));
        players.receiveCards(cards.getPartialCards(SPREAD_MULTIPLY_SIZE, cards.getSize()), SPREAD_MULTIPLY_SIZE);
    }

    public boolean canPlayerGetMoreCard(final int index) {
        final Player player = players.getPlayer(index);
        return player.canGetMoreCard();
    }

    public void spreadOneCardToPlayer(final int index, final Card card) {
        final Player player = players.getPlayer(index);
        spreadOneCard(player, card);
    }

    public void spreadOneCardToDealer(final Card card) {
        spreadOneCard(dealer, card);
    }

    public boolean canDealerGetMoreCard() {
        return dealer.canGetMoreCard();
    }

    public int calculateDealerMaxSum() {
        return dealer.calculateMaxSum();
    }

    private void spreadOneCard(final Gamer gamer, final Card card) {
        gamer.receiveCards(new Cards(List.of(card)));
    }

    public int getInitialTotalCardsSize() {
        return (DEALER_COUNT + players.getSize()) * SPREAD_MULTIPLY_SIZE;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getPlayers());
    }

    public Dealer getDealer() {
        return dealer;
    }
}
