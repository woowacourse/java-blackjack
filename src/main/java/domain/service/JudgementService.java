package domain.service;

import domain.model.*;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.ResultDto;

import java.util.ArrayList;
import java.util.List;

public class JudgementService {

    private final PersonService personService;

    public JudgementService(PersonService personService) {
        this.personService = personService;
    }

    public ResultDto getGameResult() {
        // 최종 점수 계산
        List<Player> players = personService.findAllPlayers();
        Dealer dealer = personService.getDealer();
        dealer.calculateFinalSum();

        List<PlayerResultDto> playerResultDtos = new ArrayList<>();
        for (Player player : players) {
            player.calculateFinalSum();
            judgementWinning(player, dealer);
            PlayerResultDto playerResultDto = PlayerResultDto.of(player);
            playerResultDtos.add(playerResultDto);
        }

        DealerResultDto dealerResultDto = DealerResultDto.of(dealer);
        return ResultDto.of(dealerResultDto, playerResultDtos);
    }

    public void judgementWinning(Player player, Dealer dealer) {
        if (player.isBurst() && dealer.isBurst()) {
            player.changeStatus(PlayerStatus.LOSS);
            return;
        }
        if (player.isBurst() && dealer.isAlive()) {
            player.changeStatus(PlayerStatus.LOSS);
            return;
        }
        if (player.isAlive() && dealer.isBurst()) {
            player.changeStatus(PlayerStatus.WIN);
            return;
        }
        if (player.isAlive() && dealer.isAlive()) {
            judgeStatusByDeckSum(player, dealer);
        }
    }

    private void judgeStatusByDeckSum(Player player, Dealer dealer) {
        if (player.getDeckSum() > dealer.getDeckSum()) {
            player.changeStatus(PlayerStatus.WIN);
        }

        if (player.getDeckSum() < dealer.getDeckSum()) {
            player.changeStatus(PlayerStatus.LOSS);
        }

        if (player.getDeckSum() == dealer.getDeckSum()) {
            player.changeStatus(PlayerStatus.DRAW);
        }
    }
}
