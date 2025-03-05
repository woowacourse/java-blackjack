package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Participants {

    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void spreadAllTwoCards(final List<Card> cards) {
        final int size = 2;
        dealer.receiveCards(cards.subList(0, size));
        players.receiveCards(cards.subList(size, cards.size()), size);
    }

    public boolean canPlayerGetMoreCard(final int index) {
        final Player player = players.getPlayer(index);
        return player.canGetMoreCard();
    }

    public void spreadOneCardToPlayer(final int index, final Card card) {
        final Player player = players.getPlayer(index);
        player.receiveCards(List.of(card));
    }

    public void spreadOneCardToDealer(final Card card) {
        dealer.receiveCards(List.of(card));
    }

    public boolean canDealerGetMoreCard() {
        return dealer.canGetMoreCard();
    }

    public int getParticipantSize() {
        return DEALER_COUNT + players.getSize();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
