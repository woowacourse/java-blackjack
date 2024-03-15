package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.game.Result;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players2 {

    private final List<Player> players;

    public Players2(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validateEntryNotEmpty(players);
        validateEachPlayerNameUnique(players);
    }

    private void validateEntryNotEmpty(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 플레이어가 없습니다");
        }
    }

    private void validateEachPlayerNameUnique(List<Player> players) {
        if (countUniquePlayer(players) != players.size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 플레이어의 이름이 존재합니다");
        }
    }

    private int countUniquePlayer(List<Player> players) {
        return (int) players.stream()
                .distinct()
                .count();
    }

    public static Players2 create(List<String> rawNames) {
        List<Player> players = rawNames.stream()
                .map(Name::new)
                .map(Player::new)
                .toList();

        return new Players2(players);
    }

    public void deal(CardDeck cardDeck) {
        players.forEach(player -> player.deal(cardDeck));
    }

    public void draw(Consumer<Player> drawToPlayer) {
        players.forEach(drawToPlayer);
    }

    public PlayersResult judge(Dealer dealer) {
        Map<Player, Result> results = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> Result.determineResult(player, dealer)
                ));

        return new PlayersResult(results);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
