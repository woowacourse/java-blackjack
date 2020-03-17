package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import domain.card.Deck;
import domain.result.ResultType;

public class PlayersInfo {

    private Map<Player, BettingMoney> playersInfo;

    public static PlayersInfo of(Map<String, Integer> playerNames) {
        return new PlayersInfo(playerNames);
    }

    private PlayersInfo(Map<String, Integer> playersInfo) {
        this.playersInfo = new LinkedHashMap<>();
        playersInfo
                .forEach((name, bettingMoney) -> this.playersInfo.put(new Player(name), new BettingMoney(bettingMoney)));
    }

    public void draw(Deck deck) {
        playersInfo.forEach(
                (player, bettingMoney) -> player.draw(deck)
        );
    }

    public void additionalDealOut(Deck deck, Function<String, Boolean> isYes, Consumer<Player> showResult) {
        playersInfo.forEach((player, bettingMoney) -> {
            while (player.isAvailableToDraw() && isYes.apply(player.getName())) {
                player.draw(deck);
                showResult.accept(player);
            }
        });
    }

    public Map<Player, ResultType> decideWinner(Dealer dealer) {
        Map<Player, ResultType> resultOfPlayers = new LinkedHashMap<>();

        playersInfo.forEach((player, bettingMoney) -> resultOfPlayers.put(player, player.decideResultType(dealer)));

        return Collections.unmodifiableMap(resultOfPlayers);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(playersInfo.keySet()));
    }

    public Map<Player, BettingMoney> getPlayersInfo() {
        return playersInfo;
    }
}
