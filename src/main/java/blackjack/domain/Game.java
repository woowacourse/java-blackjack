package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.CardGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ProfitDto;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int INIT_DISTRIBUTE_NUM = 2;
    public static final int PROFIT_REVERSE = -1;

    private final Dealer dealer;
    private final Users users;
    private final Deck deck;

    public Game(List<String> userNames) {
        this.users = new Users(userNames);
        this.deck = new Deck(new CardGenerator());
        this.dealer = new Dealer();
    }

    public void initBettingMoney(String userName, int money) {
        users.getUserByName(userName).betting(money);
    }

    public List<String> getUserNames() {
        return users.getUserNames();
    }

    public List<ParticipantDto> initDistributed() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        cardDistribute(dealer);
        participantDtos.add(ParticipantDto.of(dealer.getName(), dealer.getHoldingCardsWithoutHidden()));

        for (User user : users.getUsers()) {
            cardDistribute(user);
            participantDtos.add(ParticipantDto.of(user.getName(), user.getHoldingCards()));
        }

        return participantDtos;
    }

    private void cardDistribute(Participant participant) {
        for (int i = 0; i < INIT_DISTRIBUTE_NUM; i++) {
            participant.receiveCard(deck);
        }
    }

    public ParticipantDto playEachUser(String userName) {
        User user = users.getUserByName(userName);
        user.receiveCard(deck);
        return ParticipantDto.of(user.getName(), user.getHoldingCards());
    }

    public boolean checkUserBust(String userName) {
        return users.getUserByName(userName).isBust();
    }

    public boolean playDealer() {
        if (dealer.checkUnderScoreStandard()) {
            dealer.receiveCard(deck);
            return true;
        }
        return false;
    }

    public List<ParticipantDto> getDealerAndPlayerCard() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.of(dealer.getName(), dealer.getHoldingCardsWithoutHidden(), dealer.getScore()));

        for (User user : users.getUsers()) {
            participantDtos.add(ParticipantDto.of(user.getName(), user.getHoldingCards(), user.getScore()));
        }

        return participantDtos;
    }

    public List<ProfitDto> getParticipantProfits() {
        List<ProfitDto> profitDtos = new ArrayList<>();
        profitDtos.add(new ProfitDto(dealer.getName(), PROFIT_REVERSE * users.getTotalProfit(dealer.getScore())));
        for (User user : users.getUsers()) {
            profitDtos.add(new ProfitDto(user.getName(), user.calculateProfit(dealer.getScore())));
        }
        return profitDtos;
    }
}
