package blackjack.domain.machine;

import blackjack.domain.card.Cards;
import blackjack.domain.dto.RecordsDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class Blackjack {
    private static final int NUMBER_OF_INIT_CARD = 2;

    private final Cards cards;

    public Blackjack() {
        this.cards = new Cards();
    }

    public void dealInitialCards(Dealer dealer, Players players) {
        for (int i = 0; i < NUMBER_OF_INIT_CARD; ++i) {
            dealer.addCard(cards.draw());
            players.addCards(cards);
        }
    }

    public void dealAdditionalCardToPlayer(Player player) {
        player.addCard(cards.draw());
    }

    public void dealAdditionalCardToDealer(Dealer dealer) {
        dealer.addCard(cards.draw());
    }

    public RecordsDto record(Dealer dealer, Players players) {
        return Records.of(dealer, players);
    }

    public Profits profit(Dealer dealer, Players players) {
        return Profits.of(dealer, players);
    }
}