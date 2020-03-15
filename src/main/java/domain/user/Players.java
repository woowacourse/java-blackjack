package domain.user;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.result.ResultType;

public class Players {

    private List<Player> players;

    public static Players of(List<String> playerNames) {
        return new Players(playerNames);
    }

    private Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void draw(Deck deck) {
        players.forEach(
                player -> player.draw(deck)
        );
    }

    public void additionalDealOut(Deck deck, Function<String, Boolean> isYes, Consumer<Player> showResult) {
        players.forEach(player -> {
            while (player.isAvailableToDraw() && isYes.apply(player.getName())) {
                player.draw(deck);
                showResult.accept(player);
            }
        });
    }

    public Map<Player, ResultType> decideWinner(Dealer dealer) {
        Map<Player, ResultType> resultOfPlayers = new LinkedHashMap<>();

        players.forEach(player -> resultOfPlayers.put(player, player.decideResultType(dealer)));

        return Collections.unmodifiableMap(resultOfPlayers);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
