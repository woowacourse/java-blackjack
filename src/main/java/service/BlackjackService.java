package service;

import domain.Deck;
import domain.Participants;
import dto.DealerResultDTO;
import dto.ParticipantsInitDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import java.util.List;
import util.DisplayFormatter;
import util.RandomShuffleStrategy;
import view.Message;

public class BlackjackService {
    private Participants participants;
    private Deck deck;

    public void initParticipant(List<ParticipantsInitDTO> participantsInitDTOS) {
        participants = new Participants(participantsInitDTOS);
    }

    public void makeDeck() {
        deck = new Deck(new RandomShuffleStrategy());
    }

    public void dealInitialCards() {
        for (int cardCount = 0; cardCount < 2; cardCount++) {
            participants.dealOneCardToAll(deck);
        }
    }

    public String makeUserNameFormat() {
        return String.format(Message.DEAL_CARDS_MESSAGE, participants.getUserNames());
    }

    public String makeDealerOneCardDisplay() {
        return String.format(Message.DEALER_CARDS_MESSAGE, participants.getDealerOneCardDisplay());
    }

    public List<String> makeExtraCardRequests() {
        List<String> playerNames = participants.getPlayerNames();
        return playerNames.stream()
                .map(DisplayFormatter::formatExtraCardRequest)
                .toList();
    }

    public List<String> getUserCardsDisplays() {
        List<UserCardsDTO> userCardsDTOS = participants.getUserCards();
        return userCardsDTOS.stream()
                .map(this::makeOneUserCardDisplay)
                .toList();
    }

    private String makeOneUserCardDisplay(UserCardsDTO userCardsDTO) {
        return DisplayFormatter.formatUserCardsDisplay(userCardsDTO);
    }

    public String hit(int index) {
        participants.dealCard(deck, index);
        participants.calculateUserScore(index);
        UserCardsDTO userCardsDTO = participants.getPlayerCards(index);
        return DisplayFormatter.formatUserCardsDisplay(userCardsDTO);
    }

    public String stand(int index) {
        calculateUserScore(index);
        UserCardsDTO userCardsDTO = participants.getPlayerCards(index);
        return DisplayFormatter.formatUserCardsDisplay(userCardsDTO);
    }

    public void calculateDealerScore() {
        participants.calculateDealerScore();
    }

    public String dealExtraCardIfNeeded() {
        if (participants.shouldDealerDraw()) {
            participants.dealCardToDealer(deck.drawCard());
            return Message.DEALER_CARD_RECEIVE_ANNOUNCE;
        }
        return "";
    }

    public String makeDealerFinalResultDisplay() {
        DealerResultDTO dealerResultDTO = participants.getDealerResult();
        return DisplayFormatter.formatDealerResultDisplay(dealerResultDTO);
    }

    public List<String> makeUserFinalResultDisplay() {
        List<UserResultDTO> userResultDTOS = participants.getUserResults();
        return userResultDTOS.stream()
                .map(DisplayFormatter::formatUserResultDisplay)
                .toList();
    }

    private void calculateUserScore(int index) {
        participants.calculateUserScore(index);
    }

    public List<String> evaluateGame() {
        ProfitResultDTO profitResultDTO = participants.calculateProfit();
        return DisplayFormatter.formatProfitResult(profitResultDTO);
    }

    public boolean isBust(int index) {
        return participants.isBust(index);
    }
}
