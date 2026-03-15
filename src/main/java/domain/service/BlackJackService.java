package domain.service;

import domain.model.Bets;
import domain.model.Dealer;
import domain.model.Player;
import dto.*;
import repository.DealerRepository;
import repository.PlayerRepository;

import java.util.List;

import static constant.BlackJackConstant.DEALER_APPEND_CRITERIA;

public class BlackJackService {

    private final PlayerRepository playerRepository;
    private final DealerRepository dealerRepository;
    private final CardDistributor cardDistributor;
    private final JudgementService judgementService;
    private final Bets bets;

    public BlackJackService(
            PlayerRepository playerRepository, DealerRepository dealerRepository,
            CardDistributor cardDistributor,
            JudgementService judgementService,
            Bets bets
    ) {
        this.playerRepository = playerRepository;
        this.dealerRepository = dealerRepository;
        this.cardDistributor = cardDistributor;
        this.judgementService = judgementService;
        this.bets = bets;
    }

    // 플레이어 생성 후 카드 분배
    public InitialDto createPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .map(playerRepository::save)
                .toList();
        cardDistributor.initialize(players);

        return InitialDto.of(dealerRepository.getDealer(), players);
    }

    // 플레이어 추가 카드
    public PlayerResultDto additionalCard(Player player) {
        cardDistributor.distributeAdditionalCard(player);
        int sum = player.getDeckSum();
        int money = bets.getFinalMoney(player);
        return PlayerResultDto.of(player, sum, money);
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

    public void addBet(Player player, int money) {
        bets.addBet(player, money);
    }
}
