package domain.game;

import constant.Result;
import domain.card.Card;
import domain.card.CardMachine;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import dto.BlackjackResultDto;
import dto.BlackjackStatisticsDto;
import dto.DealerStatisticDto;
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

    public void createPlayers(List<String> names) {
        Dealer dealer = new Dealer();
        Players players = new Players(names);
        participants = new Participants(dealer, players);
    }

    public void drawInitialCards() {
        participants.dealer().addCard(drawCard());
        participants.dealer().addCard(drawCard());
        for (Player player : participants.players().getPlayers()) {
            player.addCard(drawCard());
            player.addCard(drawCard());
        }
    }

    public boolean drawDealerCard() {
        Dealer dealer = participants.dealer();
        if (dealer.shouldHit()) {
            dealer.addCard(drawCard());
            return true;
        }

        return false;
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }

    public ParticipantDto generateInitialDealerDto() {
        Dealer dealer = participants.dealer();
        return ParticipantDto.from(dealer, true);
    }

    public ParticipantDto generateDealerDto() {
        Dealer dealer = participants.dealer();
        return ParticipantDto.from(dealer);
    }

    public List<ParticipantDto> generatePlayerDtoList() {
        List<ParticipantDto> playersDto = new ArrayList<>();
        for (Player player : participants.players().getPlayers()) {
            playersDto.add(ParticipantDto.from(player));
        }
        return playersDto;
    }

    public BlackjackResultDto getBlackjackResult() {
        ParticipantDto dealerResultDto = generateDealerDto();
        List<ParticipantDto> playerResultDtoList = generatePlayerDtoList();

        return BlackjackResultDto.of(dealerResultDto, playerResultDtoList);
    }

    public BlackjackStatisticsDto getBlackjackStatistics() {
        List<PlayerStatisticDto> playerStatisticDtoList = calculatePlayerResults();
        int win = 0, draw = 0, lose = 0;
        for (PlayerStatisticDto playerStatisticDto : playerStatisticDtoList) {
            Result result = playerStatisticDto.result();
            win += judgeResult(result, Result.LOSE);
            draw += judgeResult(result, Result.DRAW);
            lose += judgeResult(result, Result.WIN);
        }
        return BlackjackStatisticsDto.of(DealerStatisticDto.of(win, draw, lose), playerStatisticDtoList);
    }

    public List<PlayerStatisticDto> calculatePlayerResults() {
        Dealer dealer = participants.dealer();
        List<PlayerStatisticDto> playerStatisticDtoList = new ArrayList<>();
        for (Player player : participants.players().getPlayers()) {
            Result result = calculatePlayerResult(dealer, player);
            playerStatisticDtoList.add(PlayerStatisticDto.of(player, result));
        }
        return playerStatisticDtoList;
    }

    private Result calculatePlayerResult(Dealer dealer, Player player) {
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

    private int judgeResult(Result result, Result playerResult) {
        if (result.equals(playerResult)) {
            return 1;
        }
        return 0;
    }

    public ParticipantDto updatePlayer(String name) {
        Player player = participants.getPlayer(name);
        player.addCard(drawCard());
        return ParticipantDto.from(player);
    }

    public boolean isHit(HitOrStand hitOrStand) {
        return hitOrStand.isHit();
    }

    public boolean isStand(HitOrStand hitOrStand) {
        return hitOrStand.isStand();
    }
}
