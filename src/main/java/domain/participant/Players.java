package domain.participant;

import domain.blackJack.MatchResult;
import domain.blackJack.Result;
import domain.card.CardDeck;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private static final int MAXIMUM_PLAYER_NUMBER = 6;

    private final List<Player> players;

    public static Players from(final List<Player> players) {
        return new Players(players);
    }

    private Players(final List<Player> players) {
        this.players = players;
    }

    public void hitCard(final CardDeck standard) {
        players.forEach(player -> player.hitCard(standard));
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck,
                     final CardDeck standard) {
        for (Player player : players) {
            player.draw(answer, playerDeck, standard);
        }
    }

    public LinkedHashMap<Player, Integer> calculateWinner(Dealer dealer) {
        LinkedHashMap<Player, MatchResult> matchResultOfPlayer = new LinkedHashMap<>();
        Result result = new Result();

        for (Player player : players) {
            matchResultOfPlayer.put(player, result.calculateResultOfPlayer(player, dealer));
        }

        return calculateProfit(matchResultOfPlayer);
    }

    private LinkedHashMap<Player, Integer> calculateProfit(LinkedHashMap<Player, MatchResult> res) {
        LinkedHashMap<Player, Integer> profitOfPlayer = new LinkedHashMap<>();
        for (Entry<Player, MatchResult> playerMatchResult : res.entrySet()) {
            Player player = playerMatchResult.getKey();
            profitOfPlayer.put(player, player.calculateProfit(playerMatchResult.getValue()));
        }

        return profitOfPlayer;
    }

    public static void validatePlayerNumbers(final List<String> players) {
        if (players.isEmpty() || players.size() > MAXIMUM_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }

    public static void validateIsDuplicate(final List<String> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름의 플레이어가 게임에 참여할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

}
