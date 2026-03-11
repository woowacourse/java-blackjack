package blackjack.service;

import blackjack.domain.*;

import java.util.List;

public class CardDistributor {

    private final CardPicker cardPicker;

    public CardDistributor(CardPicker cardPicker) {
        this.cardPicker = cardPicker;
    }

    public void distributeCardToPlayer(Participant player) {
        Card card = cardPicker.drawCard();
        player.receiveOneCard(card);
    }

    public void distributeCardToDealer(Participant dealer) {
        Card card = cardPicker.drawCard();
        dealer.receiveOneCard(card);
    }

    public void distributeInitialCards(List<Participant> players, Participant dealer) {
        for (Participant player : players) {
            distributeTwoCardsToPlayer(player);
        }
        distributeTwoCardsToDealer(dealer);
    }

    public void distributeTwoCardsToPlayer(Participant player) {
        player.receiveOneCard(cardPicker.drawCard());
        player.receiveOneCard(cardPicker.drawCard());
    }

    public void distributeTwoCardsToDealer(Participant dealer) {
        dealer.receiveOneCard(cardPicker.drawCard());
        dealer.receiveOneCard(cardPicker.drawCard());
    }
}
