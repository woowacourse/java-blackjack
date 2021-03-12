package blackjack.domain.participant;

import blackjack.domain.result.GameResult;
import blackjack.view.InputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private List<Player> players;

    public Players(final List<Player> players) {
        validatePlayersCount(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayersAsList() {
        return new ArrayList<>(players);
    }

    public void initBettings() {
        players = Collections.unmodifiableList(
                new ArrayList<>(
                        getPlayersAsList()
                                .stream()
                                .map(player -> player.changeBetting(InputView.getBettings(player.getNameAsString())))
                                .collect(Collectors.toList())));
    }

    private void validatePlayersCount(final List<Player> players) {
        if (players.size() < 1 || players.size() > 7) {
            throw new IllegalArgumentException("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
        }
    }

    public void calculateProfits(GameResult gameResult) {
        players = Collections.unmodifiableList(
                new ArrayList<>(
                        gameResult
                                .getGameResult()
                                .entrySet()
                                .stream()
                                .map(entry -> entry.getKey().changeProfit(entry.getValue())).collect(Collectors.toList())));
    }
}
