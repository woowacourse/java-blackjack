package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(final Participants participants) {
        this.participants = participants;
        this.deck = new Deck();
    }

    public void distributeTwoCards() {
        List<Card> cardList = new ArrayList<>();

        while (cardList.size() < participants.size()) {
            Card drawnCard = deck.drawCard();
            cardList.add(drawnCard);
        }
        participants.receiveSettingCards(cardList);
    }

    public void giveOneMoreCard(Participant participant) {
        participant.receiveCard(deck.drawCard());
    }
}
