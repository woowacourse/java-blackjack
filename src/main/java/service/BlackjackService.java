package service;

import constant.PlayerAction;
import constant.PolicyConstant;
import constant.Result;
import domain.CardMachine;
import domain.bet.Money;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.BlackjackProfitDto;
import dto.BlackjackResultDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackService {

    private final CardMachine cardMachine;
    private final Players players;
    private final Dealer dealer;

    public BlackjackService(Players players) {
        this.cardMachine = new CardMachine();
        this.players = players;
        this.dealer = new Dealer();
    }

    public void receivePlayerBets(int playerIndex, Money money) {
        Player player = players.getPlayerByIndex(playerIndex);
        player.placeBet(money);
    }

    public List<String> getAllPlayerNames() {
        return players.getAllPlayers().stream()
            .map(Participant::getName)
            .toList();
    }

    public int getPlayerCount() {
        return players.value().size();
    }

    public String getPlayerName(int playerIndex) {
        return players.getPlayerByIndex(playerIndex).getName();
    }

    public void updatePlayer(int playerIndex) {
        players.addCardPlayer(playerIndex, cardMachine.drawCard());
    }

    public void dealInitialCards() {
        for (Player player : players.value()) {
            player.addCard(List.of(cardMachine.drawCard(), cardMachine.drawCard()));
        }
        dealer.addCard(List.of(cardMachine.drawCard(), cardMachine.drawCard()));
    }

    public boolean drawDealerCard() {
        if (dealer.calculateScore() <= PolicyConstant.DEALER_HIT_MAX_SCORE) {
            dealer.addCard(List.of(cardMachine.drawCard()));
            return true;
        }
        return false;
    }

    public BlackjackProfitDto calculateBlackjackResult() {
        double dealerProfit = 0;
        List<PlayerResultDto> playerResultDtoList = new ArrayList<>();
        for (Player player : players.value()) {
            double profit = player.getBetAmount() * Result.from(dealer, player).getResult();
            dealerProfit += calculateDealerProfit(profit);
            playerResultDtoList.add(new PlayerResultDto(player.getName(), profit));
        }
        return new BlackjackProfitDto(dealerProfit, playerResultDtoList);
    }

    private double calculateDealerProfit(double profit) {
        if (profit < 0) {
            return profit * (-1);
        }
        return 0;
    }

    public ParticipantDto createPlayerDto(int playerIndex) {
        Player player = players.getPlayerByIndex(playerIndex);
        return new ParticipantDto(player.getName(), player.getHand().getCardNames());
    }

    private int calculateScore(Participant participant) {
        return participant.calculateScore();
    }

    public List<BlackjackResultDto> generateBlackjackResultDto() {
        List<BlackjackResultDto> blackjackResultDtoList = new ArrayList<>();
        addResult(dealer, blackjackResultDtoList);
        for (Player player : players.value()) {
            addResult(player, blackjackResultDtoList);
        }
        return Collections.unmodifiableList(blackjackResultDtoList);
    }

    private void addResult(Participant participant, List<BlackjackResultDto> blackjackResultDtoList) {
        int score = calculateScore(participant);
        BlackjackResultDto resultDto = new BlackjackResultDto(
            participant.getName(),
            participant.getHand().getCardNames(),
            score
        );
        blackjackResultDtoList.add(resultDto);
    }

    public boolean shouldRepeat(int playerIndex, PlayerAction playerAction) {
        return playerAction == PlayerAction.HIT && !players.getPlayerByIndex(playerIndex).isBust();
    }

    public List<ParticipantDto> getAllPlayerDto() {
        List<ParticipantDto> participantDtoList = new ArrayList<>();
        for (Player player : players.getAllPlayers()) {
            participantDtoList.add(
                new ParticipantDto(player.getName(), player.getHand().getCardNames())
            );
        }
        return participantDtoList;
    }

    public ParticipantDto getDealerPlayerDto() {
        return new ParticipantDto(
            dealer.getName(),
            dealer.getOnlyFirstHand().getCardNames()
        );
    }
}
