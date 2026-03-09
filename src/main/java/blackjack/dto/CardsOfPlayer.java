package blackjack.dto;

import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record CardsOfPlayer(
        Map<String, List<String>> cardsOfPlayer
) {

    public static CardsOfPlayer from(Players players, Player dealer) {
        Map<String, List<String>> cardsOfPlayer = new LinkedHashMap<>();
        cardsOfPlayer.put(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (Player player : players.getPlayers()) {
            cardsOfPlayer.put(player.getName(), player.getCardsName());
        }

        return new CardsOfPlayer(cardsOfPlayer);
    }

    public List<String> get(String playerName) {
        return cardsOfPlayer.get(playerName);
    }

}
