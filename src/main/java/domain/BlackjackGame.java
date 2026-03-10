package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import vo.GameResult;

public class BlackjackGame {
    private final static Integer FIRST_CARD_DEAL_COUNT = 2;

    private Participants participants;
    private Deck deck;

    public void saveParticipants(String participantsName) {
        participants = new Participants(participantsName);
    }

    public void makeDeck() {
        deck = new Deck();
    }

    void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void dealCards() {
        for (int cardCount = 0; cardCount < FIRST_CARD_DEAL_COUNT; cardCount++) {
            participants.dealCards(deck);
        }
    }

    public List<String> getParticipantNames() {
        return participants.getParticipantNames();
    }

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public void processPlayerDecision(int index) {
        participants.dealCard(deck, index);
    }

    public boolean dealToDealer() {
        if (participants.determineDealerDealMore()) {
            participants.dealCardToDealer(deck.dealCard());
            return true;
        }
        return false;
    }

    public Map<String, GameResult> getUserResults() {
        return participants.calculateUserResults();
    }

    public EnumMap<GameResult, Integer> getDealerResults() {
        return participants.calculateDealerResults();
    }
}