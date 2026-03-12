package domain.service;

import domain.model.*;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import repository.PlayerBettingRepository;

import java.util.ArrayList;
import java.util.List;

import static constant.ErrorMessage.PLAYER_BETTING_NOT_FOUND;

public class JudgementService {

    private final PersonService personService;

    public JudgementService(PersonService personService) {
        this.personService = personService;
    }

    public ResultDto getGameResult() {
        List<Player> players = personService.findAllPlayers();
        Dealer dealer = personService.getDealer();
        List<PlayerResultDto> playerResultDtos = players.stream().map(player -> {
            judge(player, dealer);
            return PlayerResultDto.of(player);
        }).toList();

        DealerResultDto dealerResultDto = DealerResultDto.of(dealer);
        return ResultDto.of(dealerResultDto, playerResultDtos);
    }

    private void judge(Player player, Dealer dealer) {
        judgementWinning(player, dealer);
        PlayerBetting playerBetting = personService.findPlayerBettingByPlayer(player);
        playerBetting.applyBetting(dealer);
    }

    public void judgementWinning(Player player, Dealer dealer) {
        if (dealer.isBurst()) {
            player.changeStatus(PlayerStatus.WIN);
            return;
        }
        if (player.isBurst() && dealer.isAlive()) {
            player.changeStatus(PlayerStatus.LOSS);
            return;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            player.changeStatus(PlayerStatus.DRAW);
            return;
        }
        if (player.isAlive() && dealer.isAlive()) {
            judgeStatusByDeckSum(player, dealer);
        }
    }

    private void judgeStatusByDeckSum(Player player, Dealer dealer) {
        if (player.getFinalDeckSum() > dealer.getFinalDeckSum()) {
            player.changeStatus(PlayerStatus.WIN);
        }

        if (player.getFinalDeckSum() < dealer.getFinalDeckSum()) {
            player.changeStatus(PlayerStatus.LOSS);
        }

        if (player.getFinalDeckSum() == dealer.getFinalDeckSum()) {
            player.changeStatus(PlayerStatus.DRAW);
        }
    }
}
