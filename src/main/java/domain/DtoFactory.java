package domain;

import dto.GameInitialInfoDto;
import dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class DtoFactory {
    private DtoFactory() {
    }

    public static List<GameScoreResultDto> toScoreResults(Dealer dealer, Players players) {
        List<GameScoreResultDto> results = new ArrayList<>();
        results.add(toScoreResult(dealer));
        addPlayerScoreResults(results, players);
        return results;
    }

    public static List<GameInitialInfoDto> toInitialInfo(Dealer dealer, Players players) {
        List<GameInitialInfoDto> results = new ArrayList<>();
        addDealerInitialInfo(results, dealer);
        addPlayerInitialInfos(results, players);
        return results;
    }

    private static GameScoreResultDto toScoreResult(Participant participant) {
        return new GameScoreResultDto(
                participant.getName(),
                participant.getHandToString(),
                participant.getScore()
        );
    }

    private static void addPlayerScoreResults(List<GameScoreResultDto> results, Players players) {
        for (Player player : players.getNonNaturalBlackJackPlayers()) {
            results.add(toScoreResult(player));
        }
    }

    private static void addDealerInitialInfo(List<GameInitialInfoDto> results, Dealer dealer) {
        results.add(new GameInitialInfoDto(
                dealer.getName(),
                List.of(dealer.getOpenCard())
        ));
    }

    private static void addPlayerInitialInfos(List<GameInitialInfoDto> results, Players players) {
        for (Player player : players.getNonNaturalBlackJackPlayers()) {
            results.add(new GameInitialInfoDto(
                    player.getName(),
                    player.getHandToString()
            ));
        }
    }
}