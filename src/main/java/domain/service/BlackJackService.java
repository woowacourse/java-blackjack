package domain.service;

import domain.model.*;
import dto.*;

import java.util.List;

public class BlackJackService {

    private final JudgementService judgementService;
    private final Players players;
    private final PlayerBettings playerBettings;
    private final Dealer dealer;

    public BlackJackService(
            JudgementService judgementService,
            Players players,
            PlayerBettings playerBettings,
            Dealer dealer
    ) {
        this.judgementService = judgementService;
        this.players = players;
        this.playerBettings = playerBettings;
        this.dealer = dealer;
    }

    // 플레이어 생성 후 카드 분배
    public InitialDto createPlayer(List<String> playerNames) {
        List<Player> allPlayers = players.saveAllPlayers(playerNames);
        for (Player player : allPlayers) {
            players.giveDeck(player, dealer.getInitialDeck());
        }
        dealer.assignDeck();
        return InitialDto.of(dealer, allPlayers);
    }

    public List<Player> getAllPlayers() {
        return players.getAllPlayers();
    }

    public void createBetting(Player player, int bettingPrice) {
        PlayerBetting playerBetting = PlayerBetting.of(player, bettingPrice);
        playerBettings.save(playerBetting);
    }

    public PlayerResultDto assignAdditionalCard(Player player) {
        Card additionalCard = dealer.getAdditionalCard();
        player.appendCard(additionalCard);
        return PlayerResultDto.of(player);
    }

    public boolean isDealerCanAppend() {
        return dealer.canAppend();
    }

    public void assignAdditionalDealerCard() {
        dealer.assignAdditionalCard();
    }

    public ResultDto getGameResult() {
        return judgementService.getGameResult(getAllPlayers(), dealer);
    }
}
