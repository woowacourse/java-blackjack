package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.profit.BetAmount;
import blackjack.domain.profit.PlayersProfit;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
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

    public static Players create(List<String> rawNames) {
        List<Player> players = rawNames.stream()
                .map(PlayerName::new)
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    public PlayersProfit bet(Function<Player, String> receiveBetAmount) {
        Map<Player, BetAmount> playersBetAmount = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> new BetAmount(receiveBetAmount.apply(player))
                ));

        return PlayersProfit.createInitial(playersBetAmount);
    }

    public void deal(CardDeck cardDeck) {
        players.forEach(player -> player.deal(cardDeck));
    }

    public void draw(Consumer<Player> drawToPlayer) {
        players.forEach(drawToPlayer);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
