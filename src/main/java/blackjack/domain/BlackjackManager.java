package blackjack.domain;

import blackjack.controller.dto.PlayerRequestDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackManager {

    private static final double LOSE_EARNING_RATE = -2.0d;
    private static final double TIE_EARNING_RATE = 0d;

    private BlackjackManager() {
    }

    public static Players createPlayers(List<PlayerRequestDto> playerRequestDtos) {
        return new Players(playerRequestDtos.stream()
                .map(PlayerRequestDto::toEntity)
                .collect(Collectors.toList()));
    }

    public static void initGame(final Players players, final Dealer dealer) {
        dealer.receiveFirstHand(dealer.drawCards());
        players.initHandByDealer(dealer);
    }

    public static List<GameResultDto> getGameResult(final Dealer dealer, final Players players) {
        List<GameResultDto> playersResultDtos = getPlayersResult(dealer, players);
        GameResultDto dealerResultDto = getDealerResult(playersResultDtos);

        List<GameResultDto> gameResultDtos = new ArrayList<>();
        gameResultDtos.add(dealerResultDto);
        gameResultDtos.addAll(playersResultDtos);
        return gameResultDtos;
    }

    private static List<GameResultDto> getPlayersResult(final Dealer dealer, final Players players) {
        return players.map(player -> new GameResultDto(player.getName(), calculateEarning(dealer, player)))
                .collect(Collectors.toList());
    }

    private static GameResultDto getDealerResult(final List<GameResultDto> playerResults) {
        double dealerEarning = playerResults.stream()
                .filter(playerResult -> playerResult.getEarning() < 0)
                .mapToDouble(GameResultDto::getEarning)
                .reduce(0.0, (a, b) -> a + (Math.abs(b) / 2));
        return new GameResultDto("딜러", dealerEarning);
    }

    private static double calculateEarning(final Dealer dealer, final Player player) {
        GameResult gameResult = dealer.judgePlayer(player);
        if (gameResult == GameResult.WIN) {
            return player.profit();
        }
        if (gameResult == GameResult.LOSE) {
            return player.profit(LOSE_EARNING_RATE);
        }
        return player.profit(TIE_EARNING_RATE);
    }
}
