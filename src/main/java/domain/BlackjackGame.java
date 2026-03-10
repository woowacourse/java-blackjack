package domain;

import java.util.List;

public class BlackjackGame {
    private final static Integer FIRST_CARD_DEAL_COUNT = 2;

    private Participants participants;
    private Deck deck;

    public void saveParticipants(List<String> parsedParticipantsName) {
        participants = new Participants(parsedParticipantsName);
    }

    public void makeDeck() {
        deck = new Deck();
    }

    void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void dealCards() {
        for(int cardCount = 0; cardCount < FIRST_CARD_DEAL_COUNT; cardCount++) {
            participants.dealCards(deck);
        }
    }

    public List<String> getParticipantNames() {
        return participants.getParticipantNames();
    }

    public String getDealerFirstCardDisplay() {
        return participants.getDealerCardsDisplay();
    }

    public List<String> getUserCardsDisplays() {
        return participants.getUserCardsDisplays();
    }

    public String processPlayerDecision(int index) {
        participants.dealCard(deck, index);
        return participants.makeOneUserCardDelegator(index);
    }

    public boolean dealToDealer() {
        if (participants.determineDealerDealMore()) {
            participants.dealCardToDealer(deck.dealCard());
            return true;
        }
        return false;
    }

    public String getDealerHandDisplay() {
        return participants.getAllDealerCardsDisplay() + participants.getDealerFinalDisplay();
    }

    public List<String> getUserFinalHandDisplays() {
        return participants.addScoreToUserHand();
    }

    public List<String> evaluateGame() {
        return participants.judgeWinner();
    }
}