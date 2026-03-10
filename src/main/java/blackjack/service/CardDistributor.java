package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

import java.util.List;

public class CardDistributor {

    private final CardPicker cardPicker;

    public CardDistributor(CardPicker cardPicker) {
        this.cardPicker = cardPicker;
    }

    public void distributeCardToPlayer(Player player) {
        Card card = cardPicker.drawCard();
        player.receiveOneCard(card);
    }

    public void distributeCardToDealer(Dealer dealer) {
        Card card = cardPicker.drawCard();
        dealer.receiveOneCard(card);
    }

    public void distributeInitialCards(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            distributeTwoCardsToPlayer(player);
        }
        distributeTwoCardsToDealer(dealer);
    }

    public void distributeTwoCardsToPlayer(Player player) {
        player.receiveOneCard(cardPicker.drawCard());
        player.receiveOneCard(cardPicker.drawCard());
    }

    public void distributeTwoCardsToDealer(Dealer dealer) {
        dealer.receiveOneCard(cardPicker.drawCard());
        dealer.receiveOneCard(cardPicker.drawCard());
    }
}
