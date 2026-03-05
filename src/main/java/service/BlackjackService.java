package service;

import domain.Deck;
import domain.Participants;
import java.util.List;
import view.Message;

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

    public String makeUserNameFormat() {
        return String.format(Message.DEAL_CARDS_MESSAGE, participants.getUserNames());
    }

    public String makeDealerCardsDisplay() {
        return String.format(Message.DEALER_CARDS_MESSAGE, participants.getDealerCardsDisplay());
    }

    public List<String> getUserCardsDisplays() {
        return participants.getUserCardsDisplays();
    }
}
