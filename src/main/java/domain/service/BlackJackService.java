package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import dto.*;
import repository.DealerRepository;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

import static constant.BlackJackConstant.DEALER_APPEND_CRITERIA;

public class BlackJackService {

    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;
    private final CardDistributor cardDistributor;
    private final JudgementService judgementService;

    public BlackJackService(
            PlayerRepository playerRepository, DealerRepository dealerRepository,
            CardDistributor cardDistributor,
            JudgementService judgementService
    ) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
        this.cardDistributor = cardDistributor;
        this.judgementService = judgementService;
    }

    // 플레이어 생성 후 카드 분배
    public InitialDto createPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .map(playerRepository::save)
                .toList();
        cardDistributor.initialize(players);

        // TODO: 출력 리팩토링 -> 객체 넘기기
        CardDto dealerCard = CardDto.of(cardDistributor.getDealer().getDeck().getLastCard());
        List<PlayerDeckDto> playerDeckDtos = new ArrayList<>();
        for (Player player : players) {
            List<CardDto> cardDtos = player.getDeck().getCards().stream()
                    .map(CardDto::of)
                    .toList();
            playerDeckDtos.add(PlayerDeckDto.of(player.getName(), cardDtos));
        }
        return new InitialDto(dealerCard, playerDeckDtos);
    }

    // 플레이어 추가 카드
    public PlayerResultDto additionalCard(Player player) {
        cardDistributor.distributeAdditionalCard(player);
        return PlayerResultDto.of(player);
    }

    public ResultDto judgement() {
        return judgementService.getGameResult();
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public boolean isDealerCanAppend() {
        Dealer dealer = dealerRepository.getDealer();
        return dealer.getDeckSum() <= DEALER_APPEND_CRITERIA;
    }

    public void additionalDealerCard() {
        Dealer dealer = dealerRepository.getDealer();
        cardDistributor.distributeAdditionalCard(dealer);
    }
}
