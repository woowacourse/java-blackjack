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

    // TODO: 최종 합산 시에 생존이라면 11로 계산했을 때 생존이면 이값으로,11로 계산했을 때 버스트면 1로 계산
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
        // 버스트 비교
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
        // 합산 비교 TODO: 마지막 조건을 if로 처리하는게 맞을까?
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
