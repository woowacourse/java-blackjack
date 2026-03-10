package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.List;

public class CardDistributor {
    public CardDistributor() {
    }

    public void distributeCardToPlayer(Player player, Card card) {
        player.receiveOneCard(card);
    }

    public void distributeCardToDealer(Dealer dealer, Card card) {
        dealer.receiveOneCard(card);
    }

    public void distributeTwoCardsToPlayer(Player player, List<Card> cards) {
        for (Card card : cards) {
            player.receiveOneCard(card);
        }
    }

    public void distributeTwoCardsToDealer(Dealer dealer, List<Card> cards) {
        for (Card card : cards) {
            dealer.receiveOneCard(card);
        }
    }
}
