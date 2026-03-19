package service;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Participants;
import domain.User;
import dto.DealerCardDTO;
import dto.DealerResultDTO;
import dto.ParticipantsInitDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import util.RandomShuffleStrategy;
import vo.Money;

public class BlackjackService {
    private Participants participants;
    private Deck deck;

    public void initParticipant(List<ParticipantsInitDTO> participantsInitDTOS) {
        List<User> users = participantsInitDTOS.stream()
                .map(dto -> new User(dto.getUserName(), dto.getBettingMoney()))
                .toList();
        participants = new Participants(users);
    }

    public void makeDeck() {
        deck = new Deck(new RandomShuffleStrategy());
    }

    public void dealInitialCards() {
        for (int cardCount = 0; cardCount < 2; cardCount++) {
            participants.dealOneCardToAll(deck);
        }
    }

    public List<String> getParticipantsNames() {
        return participants.getPlayerNames();
    }

    public DealerCardDTO getDealerFirstCard() {
        Card card = participants.getDealerFirstCard();
        return new DealerCardDTO(card.getDisplayName());
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public List<UserCardsDTO> getUserCards() {
        List<User> players = participants.getPlayers();
        return players.stream()
                .map(UserCardsDTO::fromUser)
                .collect(Collectors.toList());
    }

    public UserCardsDTO hit(int index) {
        participants.dealCard(deck, index);
        participants.calculateUserScore(index);
        User user = participants.getPlayer(index);
        return UserCardsDTO.fromUser(user);
    }

    public UserCardsDTO stand(int index) {
        calculateUserScore(index);
        User user = participants.getPlayer(index);
        return UserCardsDTO.fromUser(user);
    }

    public void calculateDealerScore() {
        participants.calculateDealerScore();
    }

    public boolean dealExtraCardIfNeeded() {
        if (participants.shouldDealerDraw()) {
            participants.dealCardToDealer(deck.drawCard());
            return true;
        }
        return false;
    }

    public DealerResultDTO makeDealerFinalResult() {
        Dealer dealer = participants.getDealer();
        return DealerResultDTO.fromDealer(dealer);
    }

    public List<UserResultDTO> makeUserFinalResult() {
        List<User> players = participants.getPlayers();
        return players.stream()
                .map(UserResultDTO::fromUser)
                .collect(Collectors.toList());
    }

    private void calculateUserScore(int index) {
        participants.calculateUserScore(index);
    }

    public ProfitResultDTO evaluateGame() {
        Map<String, Money> playersProfit = participants.calculatePlayersProfit();
        Money dealerProfit = participants.calculateDealerProfit(playersProfit);
        return new ProfitResultDTO(dealerProfit, playersProfit);
    }

    public boolean isBust(int index) {
        return participants.isBust(index);
    }
}
