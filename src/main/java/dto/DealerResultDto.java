package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import utils.GameResultConvertor;

import java.util.ArrayList;
import java.util.List;

public final class DealerResultDto {
    private final String name;
    private final String gameResult;

    public DealerResultDto(final Dealer dealer, final Players players) {
        this.name = dealer.getName();
        final List<String> results = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            results.add(GameResultConvertor.convertToString(dealer.getGameResult(player)));
        }
        this.gameResult = GameResultConvertor.convertToString(results);
    }

    public String getName() {
        return name;
    }

    public String getGameResult() {
        return gameResult;
    }
}
