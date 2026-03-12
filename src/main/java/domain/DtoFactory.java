package domain;

import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class DtoFactory {
    private static final int INITIAL_HAND_SIZE = 2;

    private DtoFactory() {
    }

    public static List<GameScoreResultDto> toScoreResults(Dealer dealer, Players players) {
        List<GameScoreResultDto> results = new ArrayList<>();
        addDealerScoreResult(results, dealer);
        addPlayerScoreResults(results, players);
        return results;
    }

    public static List<GameInitialInfoDto> toInitialInfo(Dealer dealer, Players players) {
        List<GameInitialInfoDto> results = new ArrayList<>();
        addDealerInitialInfo(results, dealer);
        addPlayerInitialInfos(results, players);
        return results;
    }

    private static void addDealerScoreResult(List<GameScoreResultDto> results, Dealer dealer) {
        results.add(new GameScoreResultDto(
                dealer.getName(),
                dealer.getHandToString(),
                dealer.getScore()
        ));
    }

    private static void addPlayerScoreResults(List<GameScoreResultDto> results, Players players) {
        for (Player player : players.getNonNaturalBlackJackPlayers()) {
            results.add(new GameScoreResultDto(
                    player.getName(),
                    player.getHandToString(),
                    player.getScore()
            ));
        }
    }

    private static void addDealerInitialInfo(List<GameInitialInfoDto> results, Dealer dealer) {
        results.add(new GameInitialInfoDto(
                dealer.getName(),
                INITIAL_HAND_SIZE,
                List.of(dealer.getOpenCard())
        ));
    }

    private static void addPlayerInitialInfos(List<GameInitialInfoDto> results, Players players) {
        for (Player player : players.getNonNaturalBlackJackPlayers()) {
            results.add(new GameInitialInfoDto(
                    player.getName(),
                    INITIAL_HAND_SIZE,
                    player.getHandToString()
            ));
        }
    }
}
