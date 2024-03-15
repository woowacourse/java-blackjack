package util;

import java.util.List;
import model.participant.Player;
import model.participant.dto.FaceUpResult;
import model.participant.dto.PlayerMatchResult;

public class ResultMapper {
    private ResultMapper() {
        throw new IllegalStateException("Static Util Class");
    }

    public static List<FaceUpResult> toPlayerFaceUpResult(List<Player> players) {
        return players.stream()
                .map(player -> new FaceUpResult(player.getName(), player.getCards()))
                .toList();
    }

    public static List<PlayerMatchResult> toPlayerMatchResults(List<Player> players, int dealerHand) {
        return players.stream()
                .map(player -> new PlayerMatchResult(player.getName(), player.calculateMatchResult(dealerHand)))
                .toList();
    }
}
