package domain.service;

import domain.model.Dealer;
import domain.model.Deck;
import domain.model.Player;
import dto.PlayerResultDto;
import repository.DealerRepository;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class judgementService {

    // TODO: 최종 합산 시에 생존이라면 11로 계산했을 때 생존이면 이값으로,11로 계산했을 때 버스트면 1로 계산
    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;

    public judgementService(PlayerRepository playerRepository, DealerRepository dealerRepository) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
    }

    public void judgement() {
        // 최종 점수 계산
        List<Player> players = playerRepository.findAll();
        List<PlayerResultDto> playerResultDtos = new ArrayList<>();
        for (Player player : players) {
            player.calculateFinalSum();
            Dealer dealer = dealerRepository.getDealer();
//          TODO: dealer. 최종 점수 계산 로직

            // 승패 판정
            Deck playerDeck = player.getDeck();
            Deck dealerDeck = dealer.getDeck();

            // 버스트 비교


            // 합산 비교


//            playerResultDtos.add(PlayerResultDto.of(player, ));
        }
    }


}
