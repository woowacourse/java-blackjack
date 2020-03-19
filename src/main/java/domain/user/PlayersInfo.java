package domain.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.card.Deck;

public class PlayersInfo {

    private Map<Player, BettingMoney> playersInfo;

    public static PlayersInfo of(Map<String, Integer> playerNames) {
        return new PlayersInfo(playerNames);
    }

    private PlayersInfo(Map<String, Integer> playersInfo) {
        this.playersInfo = new LinkedHashMap<>();
        playersInfo
                .forEach(
                        (name, bettingMoney) -> this.playersInfo.put(new Player(name), new BettingMoney(bettingMoney)));
    }

    public void draw(Deck deck) {
        playersInfo.forEach(
                (player, bettingMoney) -> player.draw(deck)
        );
    }

    public void additionalDealOut(Deck deck, Function<String, Boolean> isYes, Consumer<Player> showResult) {
        playersInfo.forEach((player, bettingMoney) -> {
            while (player.isAvailableToDraw() && isYes.apply(player.name.getName())) {
                player.draw(deck);
                showResult.accept(player);
            }
        });
    }

    public Map<User, Integer> calculatePoint() {
        return playersInfo.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                        Player::calculatePoint,
                        (e1, e2) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new));
    }

    public Map<Player, Integer> calculateProfit(Dealer dealer) {
        Map<Player, Integer> profitOfPlayers = new LinkedHashMap<>();

        playersInfo.forEach((player, bettingMoney) ->
                profitOfPlayers.put(player, (int)(bettingMoney.getMoney() * player.decideRatio(dealer).getRatio())));

        return profitOfPlayers;
    }

    public int calculateTotalProfit(Dealer dealer) {
        return playersInfo.keySet()
                .stream()
                .mapToInt(player -> (int)(playersInfo.get(player).getMoney() * player.decideRatio(dealer).getRatio()))
                .sum();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(playersInfo.keySet());
    }
}
