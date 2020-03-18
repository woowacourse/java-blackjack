package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import domain.card.Deck;
import view.dto.UserDto;

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

    public void additionalDealOut(Deck deck, Function<String, Boolean> isYes, Consumer<UserDto> showResult) {
        playersInfo.forEach((player, bettingMoney) -> {
            while (player.isAvailableToDraw() && isYes.apply(UserDto.of(player).getName())) {
                player.draw(deck);
                showResult.accept(UserDto.of(player));
            }
        });
    }

    public Map<Player, Double> calculateProfit(Dealer dealer) {
        Map<Player, Double> profitOfPlayers = new LinkedHashMap<>();

        playersInfo.forEach((player, bettingMoney) ->
                        profitOfPlayers.put(player, bettingMoney.getMoney() * player.decideRatio(dealer).getRatio()));

        return Collections.unmodifiableMap(profitOfPlayers);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(new ArrayList<>(playersInfo.keySet()));
    }

    public Map<Player, BettingMoney> getPlayersInfo() {
        return playersInfo;
    }
}
