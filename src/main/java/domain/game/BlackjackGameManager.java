package domain.game;

import domain.card.Card;
import domain.card.CardMachine;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.ParticipantDto;
import dto.PlayerStatisticDto;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGameManager {

    private final CardMachine cardMachine;
    private Participants participants;

    public BlackjackGameManager() {
        this.cardMachine = new CardMachine();
    }

    public void createParticipants(List<String> names) {
        participants = Participants.from(names);
    }

    public void drawInitialCards() {
        participants.drawInitialCards(this::drawCard);
    }

    public boolean drawDealerCard() {
        if (participants.dealerShouldHit()) {
            participants.drawCardsByDealer(this::drawCard);
            return true;
        }

        return false;
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }

    public ParticipantDto updatePlayer(String name) {
        Player player = participants.drawCardsByPlayer(name, this::drawCard);
        return ParticipantDto.from(player);
    }

    public ParticipantDto generateInitialDealerDto() {
        return ParticipantDto.from(participants.dealer(), true);
    }

    public ParticipantDto generateDealerDto() {
        return ParticipantDto.from(participants.dealer());
    }

    public List<ParticipantDto> generatePlayerDtoList() {
        return participants.players().getPlayers().stream()
                .map(ParticipantDto::from)
                .toList();
    }

    public BlackjackResultDto getBlackjackResult() {
        ParticipantDto dealerResultDto = generateDealerDto();
        List<ParticipantDto> playerResultDtoList = generatePlayerDtoList();

        return BlackjackResultDto.of(dealerResultDto, playerResultDtoList);
    }

    public BlackjackStatisticsDto getBlackjackStatistics() {
        List<PlayerStatisticDto> playerStatisticDtoList = new ArrayList<>();
        int dealerProfit = 0;
        for (Player player : participants.players().getPlayers()) {
            Result result = judgePlayerResult(participants.dealer(), player);
            int playerProfit = calculatePlayerProfit(player, result);
            dealerProfit += playerProfit * -1;
            playerStatisticDtoList.add(PlayerStatisticDto.of(player, playerProfit));
        }

        return BlackjackStatisticsDto.of(dealerProfit, playerStatisticDtoList);
    }

    private Result judgePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private int calculatePlayerProfit(Player player, Result result) {
        if (result.equals(Result.WIN) && player.isBlackjack()) {
            return player.getBetAmount() + player.getBetAmount() / 2;
        }
        if (result.equals(Result.WIN)) {
            return player.getBetAmount();
        }
        if (result.equals(Result.LOSE)) {
            return player.getBetAmount() * -1;
        }
        return 0;
    }

    public boolean isHit(HitOrStand hitOrStand) {
        return hitOrStand.isHit();
    }

    public boolean isStand(HitOrStand hitOrStand) {
        return hitOrStand.isStand();
    }

    public void setBetAmount(String name, BetAmount betAmount) {
        Player player = participants.getPlayer(name);
        player.setBetAmount(betAmount);
    }
}
