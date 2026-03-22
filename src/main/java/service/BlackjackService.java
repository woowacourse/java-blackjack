package service;

import constant.PlayerAction;
import constant.PolicyConstant;
import domain.bet.Result;
import domain.card.CardMachine;
import domain.participant.Dealer;
import domain.participant.Name;
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

    public List<Name> getPlayerNames() {
        return players.getAllPlayers().stream()
            .map(Player::getName)
            .toList();
    }

    public List<String> getPlayerNameValues() {
        return getPlayerNames().stream()
            .map(Name::value)
            .toList();
    }

    public int getPlayerCount() {
        return players.getAllPlayers().size();
    }

    public void addCard(Name name) {
        players.addCardPlayer(name, cardMachine.drawCard());
    }

    public int getPlayerScore(Name name) {
        return players.calculateScore(name);
    }

    public void dealInitialCards() {
        for (Player player : players.getAllPlayers()) {
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
        int dealerProfit = 0;
        List<PlayerResultDto> playerResultDtoList = new ArrayList<>();
        for (Player player : players.getAllPlayers()) {
            int profit = (int) (player.getBetAmount() * Result.from(dealer, player).getResult());
            dealerProfit += calculateDealerProfit(profit);
            playerResultDtoList.add(new PlayerResultDto(player.getNameValue(), profit));
        }
        return new BlackjackProfitDto(dealerProfit, playerResultDtoList);
    }

    private int calculateDealerProfit(int profit) {
        if (profit < 0) {
            return profit * (-1);
        }
        return 0;
    }

    public ParticipantDto createPlayerDto(Name name) {
        Player player = players.getPlayerByName(name);
        return new ParticipantDto(player.getNameValue(), player.getCardNames());
    }

    public List<BlackjackResultDto> createBlackjackResultDto() {
        List<BlackjackResultDto> blackjackResultDtoList = new ArrayList<>();
        addResult(dealer, blackjackResultDtoList);
        for (Player player : players.getAllPlayers()) {
            addResult(player, blackjackResultDtoList);
        }
        return Collections.unmodifiableList(blackjackResultDtoList);
    }

    private void addResult(Participant participant, List<BlackjackResultDto> blackjackResultDtoList) {
        int score = participant.calculateScore();
        BlackjackResultDto resultDto = new BlackjackResultDto(
            participant.getNameValue(),
            participant.getCardNames(),
            score
        );
        blackjackResultDtoList.add(resultDto);
    }

    public boolean shouldRepeat(Name name, PlayerAction playerAction) {
        return playerAction == PlayerAction.HIT && !players.getPlayerByName(name).isBust();
    }

    public List<ParticipantDto> getAllPlayerDto() {
        List<ParticipantDto> participantDtoList = new ArrayList<>();
        for (Player player : players.getAllPlayers()) {
            participantDtoList.add(new ParticipantDto(player.getNameValue(), player.getCardNames()));
        }
        return participantDtoList;
    }

    public ParticipantDto getDealerPlayerDto() {
        return new ParticipantDto(
            dealer.getNameValue(),
            dealer.getOnlyFirstHand().getCardNames()
        );
    }
}
