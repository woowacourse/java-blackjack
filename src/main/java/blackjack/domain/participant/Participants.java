package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Participants {

    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void spreadAllTwoCards(final Cards cards) {
        final int size = 2;
        dealer.receiveCards(cards.getPartialCards(0, size));
        players.receiveCards(cards.getPartialCards(size, cards.getSize()), size);
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

    private void spreadOneCard(final Gamer gamer, final Card card) {
        gamer.receiveCards(new Cards(List.of(card)));
    }

    public boolean canDealerGetMoreCard() {
        return dealer.canGetMoreCard();
    }

    public int getParticipantSize() {
        return DEALER_COUNT + players.getSize();
    }

    public int calculateDealerMaxSum() {
        return dealer.calculateMaxSum();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
