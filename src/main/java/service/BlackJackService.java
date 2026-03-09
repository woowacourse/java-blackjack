package service;

import domain.card.CardDeck;
import domain.participant.*;

public class BlackJackService {

    public void dealInitialCards(CardDeck cardDeck, ParticipantGroup participantGroup) {
        divideCard(cardDeck, participantGroup.getDealer());

        for (Player player : participantGroup.getPlayers().getAllPlayers()) {
            divideCard(cardDeck, player);
        }
    }

    private void divideCard(CardDeck cardDeck, Participant participant){
        for (int i = 0; i < 2; i++) {
            participant.keepCard(cardDeck.drawCard());
        }
    }
}
