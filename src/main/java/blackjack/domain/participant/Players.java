package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private List<Player> players;

    public Players(final List<Cards> cards, final List<String> playerNames) {
        players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(cards.get(i), playerNames.get(i)));
        }
        validatePlayersCount(players);
        validatePlayersCountMatchesCardsCounts(playerNames, cards);
    }

    private void validatePlayersCountMatchesCardsCounts(List<String> playerNames, List<Cards> cards) {
        if (playerNames.size() != cards.size()) {
            throw new IllegalArgumentException("플레이어에게 나눠줄 카드 뭉치 숫자와 플레이어 수는 동일해야 합니다.");
        }
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
