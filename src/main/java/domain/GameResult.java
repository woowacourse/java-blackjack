package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

public class GameResult {
    private final Map<Player, WinningStatus> playerWinningStatus = new LinkedHashMap<>();

    public GameResult(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            playerWinningStatus.put(player, WinningStatus.of(player, dealer));
        }
    }

    public List<PlayerResultInfo> getPlayersResult() {
        List<PlayerResultInfo> result = new ArrayList<>();

        for (Map.Entry<Player, WinningStatus> entry : playerWinningStatus.entrySet()) {
            String name = entry.getKey().name();
            WinningStatus status = entry.getValue();

            result.add(new PlayerResultInfo(name, status));
        }

        return result;
    }

    public DealerResultInfo getDealerResult() {
        int winCount = frequency(playerWinningStatus.values(), WinningStatus.LOSE);
        int tieCount = frequency(playerWinningStatus.values(), WinningStatus.TIE);
        int loseCount = frequency(playerWinningStatus.values(), WinningStatus.WIN);

        return new DealerResultInfo(winCount, tieCount, loseCount);
    }
}
