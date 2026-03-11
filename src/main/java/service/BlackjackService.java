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

    public void dealInitialCards() {
        for (int cardCount = 0; cardCount < 2; cardCount++) {
            participants.dealOneCardToAll(deck);
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

    public String hit(int index) {
        participants.dealCard(deck, index);
        participants.calculateUserScore(index);
        return participants.getPlayerCardStatus(index);
    }

    public String stand(int index) {
        calculateUserScore(index);
        return participants.getPlayerCardStatus(index);
    }

    public void calculateDealerScore() {
        participants.calculateDealerScore();
    }

    public String dealExtraCardIfNeeded() {
        if (participants.shouldDealerDraw()) {
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

    private void calculateUserScore(int index) {
        participants.calculateUserScore(index);
    }

    public List<String> evaluateGame() {
        return participants.makeFinalWinnerDisplays();
    }
}
