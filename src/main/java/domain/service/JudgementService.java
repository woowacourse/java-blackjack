package domain.service;

import domain.model.*;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import dto.ResultDto;

import java.util.List;

public class JudgementService {

    private final PlayerBettings playerBettings;

    public JudgementService(PlayerBettings playerBettings) {
        this.playerBettings = playerBettings;
    }

    public ResultDto getGameResult(List<Player> players, Dealer dealer) {
        players.forEach(player -> judge(player, dealer));
        dealer.applyPlayerProfit(playerBettings.calculateAllPlayerBettings());

        List<PlayerResultDto> playerResultDtos = players.stream()
                .map(PlayerResultDto::of)
                .toList();
        DealerResultDto dealerResultDto = DealerResultDto.of(dealer);
        return ResultDto.of(dealerResultDto, playerResultDtos);
    }

    private void judge(Player player, Dealer dealer) {
        player.changeStatus(judgementWinning(player.getDeck(), dealer.getDeck()));
    }

    public PlayerStatus judgementWinning(Deck playerDeck, Deck dealerDeck) {
        if (playerDeck.isBurst() || (playerDeck.isAlive() && dealerDeck.isBlackJack())) {
            return PlayerStatus.LOSS;
        }
        if (playerDeck.isBlackJack() && dealerDeck.isBlackJack()) {
            return PlayerStatus.DRAW;
        }
        if (dealerDeck.isBurst() || (playerDeck.isBlackJack() && dealerDeck.isAlive())) {
            return PlayerStatus.WIN;
        }
        if (playerDeck.isAlive() && dealerDeck.isAlive()) {
            return getPlayerStatusByDeckSum(playerDeck.calculateFinalSum(), dealerDeck.calculateFinalSum());
        }
        return PlayerStatus.NONE;
    }

    private PlayerStatus getPlayerStatusByDeckSum(int playerDeckSum, int dealerDeckSum) {
        if (playerDeckSum > dealerDeckSum) {
            return PlayerStatus.WIN;
        }
        if (playerDeckSum < dealerDeckSum) {
            return PlayerStatus.LOSS;
        }
        return PlayerStatus.DRAW;
    }
}
