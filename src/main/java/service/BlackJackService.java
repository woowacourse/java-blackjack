package service;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantGroup;
import domain.participant.Player;

public class BlackJackService {
    public void dealInitialCards(CardDeck cardDeck, ParticipantGroup participantGroup) {
        divideCard(cardDeck, participantGroup.getDealer());

        for (Player player : participantGroup.getPlayers().getAllPlayers()) {
            divideCard(cardDeck, player);
        }
    }

    public int dealerHitOrStand(Dealer dealer, CardDeck cardDeck) {
        int hitCount = 0;
        while (dealer.shouldHit()) {
            dealer.keepCard(cardDeck.drawCard());
            hitCount++;
        }
        return hitCount;
    }

    private void divideCard(CardDeck cardDeck, Participant participant) {
        for (int i = 0; i < 2; i++) {
            participant.keepCard(cardDeck.drawCard());
        }
    }
}
