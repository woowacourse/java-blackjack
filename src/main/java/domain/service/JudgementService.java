package domain.service;

import domain.model.*;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import repository.PlayerBettingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constant.ErrorMessage.PLAYER_BETTING_NOT_FOUND;

public class JudgementService {

    private final PersonService personService;
    private final PlayerBettingRepository playerBettingRepository;

    public JudgementService(PersonService personService, PlayerBettingRepository playerBettingRepository) {
        this.personService = personService;
        this.playerBettingRepository = playerBettingRepository;
    }

    public ResultDto getGameResult() {
        List<Player> players = personService.findAllPlayers();
        Dealer dealer = personService.getDealer();

        List<PlayerResultDto> playerResultDtos = new ArrayList<>();
        for (Player player : players) {
            judgementWinning(player, dealer);
            PlayerBetting playerBetting = getPlayerBetting(player);
            playerBetting.applyBetting(dealer);
            PlayerResultDto playerResultDto = PlayerResultDto.of(player);
            playerResultDtos.add(playerResultDto);
        }

        DealerResultDto dealerResultDto = DealerResultDto.of(dealer);
        return ResultDto.of(dealerResultDto, playerResultDtos);
    }

    private PlayerBetting getPlayerBetting(Player player) {
        return playerBettingRepository.findByPlayer(player)
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_BETTING_NOT_FOUND.getMessage()));
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
