package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import utils.GameResultConvertor;

import java.util.ArrayList;
import java.util.List;

public final class PlayerResultDto {
    private final String name;
    private final String gameResult;

    public PlayerResultDto(final String name, final String gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public static List<PlayerResultDto> generateDtos(final Dealer dealer, final Players players) {
        final List<PlayerResultDto> dtos = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            dtos.add(new PlayerResultDto(player.getName(), GameResultConvertor.convertToString(player.getGameResult(dealer))));
        }
        return dtos;
    }

    public String getName() {
        return name;
    }

    public String getGameResult() {
        return gameResult;
    }
}
