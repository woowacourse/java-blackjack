package domain.participant;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Players {

    private final List<Player> players;

    public static Players from(List<String> names){
        validatePlayerNumbers(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    private Players(List<Player> players) {
        this.players = players;
    }

    public void hitCards(Dealer dealer) {
        players.forEach(player -> player.hitCards(dealer));
    }

    public void draw(BooleanSupplier answer, Consumer<Player> playerDeck, Dealer dealer) {
        for (Player player : players) {
            while (answer.getAsBoolean()) {
                player.addCard(dealer);
                playerDeck.accept(player);
                    if(player.isBust()) {
                        break;
                    }
            }
        }
    }

    private static void validatePlayerNumbers(List<String> names) {
        if (names.isEmpty() || names.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 플레이어 인원은 1~6명 입니다.");
        }
    }
}
