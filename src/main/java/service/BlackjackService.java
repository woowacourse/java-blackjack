package service;

import domain.Deck;
import domain.Participants;
import java.util.List;
import view.Message;
import vo.Money;

public class BlackjackService {
    private Participants participants;
    private Deck deck;

    public void saveParticipants(List<String> parsedParticipantsName, List<Money> parsedBetAmounts) {
        participants = new Participants(parsedParticipantsName, parsedBetAmounts);
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

    public List<String> makeExtraCardRequests() {
        return participants.askGetExtraCard();
    }

    public List<String> getUserCardsDisplays() {
        return participants.getUserCardsDisplays();
    }

    public String processPlayerDecision(int index) {
        participants.dealCard(deck, index);
        participants.calculateScore(index);
        return participants.makeOneUserCardDelegator(index);
    }

    public void calculateDealerScore() {
        participants.caculateDealerscore();
    }

    public String determineDealToDealer() {
        if (participants.determineDealerDealMore()) {
            participants.dealCardToDealer(deck.dealCard());
            return Message.DEALER_CARD_RECEIVE_ANNOUNCE;
        }
        return "";
    }

    public String makeDealerFinalResultDisplay() {
        return makeDealerCardsDisplay() + participants.getDealerFinalDisplay();
    }

    public List<String> makeUserFinalResultDisplay() {
        return participants.addScoreToUserHand();
    }

    public void calculateScore(int index) {
        participants.calculateScore(index);
    }

    public List<String> evaluateGame() {
        return participants.makeFinalWinnerDisplays();
    }
}
