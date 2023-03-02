package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class BlackJackGame {

    public static void initSettingCards(Players players, Dealer dealer) {
        distributeCard(dealer, 2);
        for (Player player : players.getPlayers()) {
            distributeCard(player, 2);
        }
    }

    public static void distributeCard(Participant participant, int num) {
        for (int generateIndex = 0; generateIndex < num; generateIndex++) {
            participant.takeCard(generateCard());
        }
    }

    private static Card generateCard() {
        String cardName = Deck.drawCard();
        int cardValue = Deck.extractCardNumber(cardName);
        return new Card(cardName, cardValue);
    }
}
