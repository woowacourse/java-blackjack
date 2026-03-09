package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.CardPicker;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

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

    public void distributeTwoCardsToPlayer(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = cardPicker.drawCard();
            player.receiveOneCard(card);
        }
    }

    public void distributeTwoCardsToDealer(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            Card card = cardPicker.drawCard();
            dealer.receiveOneCard(card);
        }
    }
}
