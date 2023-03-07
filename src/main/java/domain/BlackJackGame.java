package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Participant;

public class BlackJackGame {
    public static void distributeCard(Participant participant, int num) {
        for (int generateIndex = 0; generateIndex < num; generateIndex++) {
            participant.drawCard(generateCard());
        }
    }

    private static Card generateCard() {
        String card = Deck.drawCard();
        return new Card(card, Deck.extractCardNumber(card));
    }
}
