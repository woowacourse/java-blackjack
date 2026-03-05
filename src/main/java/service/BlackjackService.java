package service;

import domain.Deck;
import domain.Participants;
import java.util.List;

public class BlackjackService {
    private Participants participants;
    private Deck deck;

    public void saveParticipants(List<String> parsedParticipantsName) {
        participants = new Participants(parsedParticipantsName);
    }

    public void makeDeck() {
        deck = new Deck();
    }

    public void dealCards() {
        for(int cardCount = 0; cardCount < 2; cardCount++) {
            participants.dealCards(deck);
        }
    }
}
