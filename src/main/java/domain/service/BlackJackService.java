package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import dto.*;

import java.util.List;

import static constant.BlackJackConstant.DEALER_APPEND_CRITERIA;

public class BlackJackService {

    private final PersonService personService;
    private final CardDistributor cardDistributor;
    private final JudgementService judgementService;

    public BlackJackService(
            PersonService personService,
            CardDistributor cardDistributor,
            JudgementService judgementService
    ) {
        this.personService = personService;
        this.cardDistributor = cardDistributor;
        this.judgementService = judgementService;
    }

    // 플레이어 생성 후 카드 분배
    public InitialDto createPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .toList();
        cardDistributor.initialize(players);
        return InitialDto.of(cardDistributor.getDealer(), players);
    }

    public List<Player> getAllPlayers() {
        return personService.findAllPlayers();
    }

    public PlayerResultDto additionalCard(Player player) {
        cardDistributor.distributeAdditionalCard(player);
        return PlayerResultDto.of(player);
    }

    public ResultDto judgement() {
        return judgementService.getGameResult();
    }

    public boolean isDealerCanAppend() {
        Dealer dealer = personService.getDealer();
        return dealer.getDefaultDeckSum() <= DEALER_APPEND_CRITERIA;
    }

    public void additionalDealerCard() {
        Dealer dealer = personService.getDealer();
        cardDistributor.distributeAdditionalCard(dealer);
    }
}
