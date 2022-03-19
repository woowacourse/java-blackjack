package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.CardGenerator;
import blackjack.domain.participant.*;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;

import java.util.ArrayList;
import java.util.List;

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

    public void initBettingMoney(String userName, int money) {
        participants.betting(userName, money);
    }

    public List<ParticipantDto> initDistributed() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        cardDistribute(DEALER_NAME);
        participantDtos.add(participants.createDealerCardInfoDto());

        for (String userName : participants.getUserNames()) {
            cardDistribute(userName);
            participantDtos.add(participants.createUserCardInfoDto(userName));
        }

        return participantDtos;
    }

    private void cardDistribute(String name) {
        for (int i = 0; i < INIT_DISTRIBUTE_NUM; i++) {
            participants.receiveCard(name, deck);
        }
    }

    public ParticipantDto playEachUser(String userName) {
        participants.receiveCard(userName, deck);
        return participants.createUserCardInfoDto(userName);
    }

    public boolean checkUserBust(String userName) {
        return participants.checkUserBust(userName);
    }

    public void changeUserStateToStand(String userName) {
        participants.changeUserStateToStand(userName);
    }

    public boolean playDealer() {
        if (participants.checkDealerScoreStandard()) {
            participants.receiveCard(DEALER_NAME, deck);
            return true;
        }
        return false;
    }

    public List<ParticipantDto> getDealerAndPlayerCard() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(participants.createDealerCardAndScoreDto());

        for (String userName : participants.getUserNames()) {
            participantDtos.add(participants.createUserCardAndScoreDto(userName));
        }

        return participantDtos;
    }

    public List<ProfitDto> getParticipantProfits() {
        List<ProfitDto> profitDtos = new ArrayList<>();
        profitDtos.add(participants.createDealerProfitDto());
        for (String userName : participants.getUserNames()) {
            profitDtos.add(participants.createUserProfitDto(userName));
        }
        return profitDtos;
    }
}
