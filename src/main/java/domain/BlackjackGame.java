package domain;

import java.util.List;
import view.Message;

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

    public String makeDealCompleteDisplay() {
        return String.format(Message.DEAL_CARDS_MESSAGE, participants.getUserNames());
    }

    public String makeDealerCardsDisplay() {
        return String.format(Message.DEALER_CARDS_MESSAGE, participants.getDealerCardsDisplay());
    }

    private String makeAllDealerCardsDisplay() {
        return String.format(Message.DEALER_FINAL_CARDS_MESSAGE, participants.getAllDealerCardsDisplay());
    }

    public List<String> makeExtraCardRequests() {
        return participants.askToGetExtraCard();
    }

    public List<String> getUserCardsDisplays() {
        return participants.getUserCardsDisplays();
    }

    public String processPlayerDecision(int index) {
        participants.dealCard(deck, index);
        return participants.makeOneUserCardDelegator(index);
    }

    public String determineDealToDealer() {
        if (participants.determineDealerDealMore()) {
            participants.dealCardToDealer(deck.dealCard());
            return Message.DEALER_CARD_RECEIVE_ANNOUNCE;
        }
        return "";
    }

    public String makeDealerFinalResultDisplay() {
        return makeAllDealerCardsDisplay() + participants.getDealerFinalDisplay();
    }

    public List<String> makeUserFinalResultDisplay() {
        return participants.addScoreToUserHand();
    }

    public List<String> evaluateGame() {
        return participants.judgeWinner();
    }
}
