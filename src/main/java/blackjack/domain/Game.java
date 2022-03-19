package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.CardGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Users;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    public static final String DEALER_NAME = "딜러";
    private static final int INIT_DISTRIBUTE_NUM = 2;

    private final Deck deck;
    private final Participants participants;

    public Game(List<String> userNames) {
        this.participants = new Participants(new Users(userNames), new Dealer(DEALER_NAME));
        this.deck = new Deck(new CardGenerator());
    }

    public List<String> getUserNames() {
        return participants.getUserNames();
    }

    public void initBettingMoney(String name, int money) {
        participants.betting(name, money);
    }

    public List<ParticipantDto> initDistributed() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        cardDistribute(DEALER_NAME);
        participantDtos.add(participants.createDealerCardInfoDto());

        for (String name : participants.getUserNames()) {
            cardDistribute(name);
            participantDtos.add(participants.createUserCardInfoDto(name));
        }

        return participantDtos;
    }

    private void cardDistribute(String name) {
        for (int i = 0; i < INIT_DISTRIBUTE_NUM; i++) {
            participants.drawCard(name, deck);
        }
    }

    public ParticipantDto playEachUser(String name) {
        participants.drawCard(name, deck);
        return participants.createUserCardInfoDto(name);
    }

    public boolean checkUserNotBust(String name) {
        return !participants.checkUserBust(name);
    }

    public void changeUserStateToStand(String name) {
        participants.changeUserStateToStand(name);
    }

    public boolean playDealer() {
        if (participants.checkDealerScoreStandard()) {
            participants.drawCard(DEALER_NAME, deck);
            return true;
        }
        return false;
    }

    public List<ParticipantDto> getDealerAndPlayerCard() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(participants.createDealerCardAndScoreDto());

        for (String name : participants.getUserNames()) {
            participantDtos.add(participants.createUserCardAndScoreDto(name));
        }

        return participantDtos;
    }

    public List<ProfitDto> getParticipantProfits() {
        List<ProfitDto> profitDtos = new LinkedList<>();
        for (String userName : participants.getUserNames()) {
            profitDtos.add(participants.createUserProfitDto(userName));
        }
        List<Double> profits = profitDtos.stream()
                .map(ProfitDto::getProfit)
                .collect(Collectors.toList());
        profitDtos.add(0, participants.createDealerProfitDto(profits));
        return profitDtos;
    }
}
