package domain.service;

import domain.model.*;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.ResultDto;
import repository.DealerRepository;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class JudgementService {

    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;

    public JudgementService(PlayerRepository playerRepository, DealerRepository dealerRepository) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
    }

    public ResultDto getGameResult() {
        // 최종 점수 계산
        List<Player> players = playerRepository.findAll();
        Dealer dealer = dealerRepository.getDealer();
        int dealerFinalScore = dealer.calculateFinalSum();
        List<PlayerResultDto> playerResultDtos = new ArrayList<>();
        for (Player player : players) {
            int playerFinalScore = player.calculateFinalSum();
            judgementWinning(player, dealer);
            PlayerResultDto playerResultDto = PlayerResultDto.of(player, playerFinalScore);
            playerResultDtos.add(playerResultDto);
        }

        DealerResultDto dealerResultDto = DealerResultDto.of(dealer, dealerFinalScore);
        return ResultDto.of(dealerResultDto, playerResultDtos);
    }

    public void judgementWinning(Player player, Dealer dealer) {
        // 버스트 비교
        if (player.isBurst()) {
            player.changeStatus(PlayerStatus.LOSS);
            return;
        }
        if (player.isAlive() && dealer.isBurst()) {
            player.changeStatus(PlayerStatus.WIN);
            return;
        }
        // 합산 비교
        if (player.isAlive() && dealer.isAlive()) {
            judgeStatusByDeckSum(player, dealer);
        }
    }

    private void judgeStatusByDeckSum(Player player, Dealer dealer) {
        if (player.calculateFinalSum() > dealer.calculateFinalSum()) {
            player.changeStatus(PlayerStatus.WIN);
        }

        if (player.calculateFinalSum() < dealer.calculateFinalSum()) {
            player.changeStatus(PlayerStatus.LOSS);
        }

        if (player.calculateFinalSum() == dealer.calculateFinalSum()) {
            player.changeStatus(PlayerStatus.DRAW);
        }
    }
}
