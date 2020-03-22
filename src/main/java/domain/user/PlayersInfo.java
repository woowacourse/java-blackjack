package domain.user;

import domain.card.Deck;
import view.dto.UserDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayersInfo {

    private final Map<Player, BettingMoney> playersInfo;

    private PlayersInfo(Map<String, Integer> playersInfo) {
        this.playersInfo = playersInfo.entrySet()
            .stream()
            .collect(Collectors.toMap(entry -> new Player(entry.getKey()),
                    entry -> new BettingMoney(entry.getValue()),
                    (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static PlayersInfo of(Map<String, Integer> playerNames) {
        return new PlayersInfo(playerNames);
    }

    public void draw(Deck deck) {
        playersInfo.forEach(
                (player, bettingMoney) -> player.draw(deck)
        );
    }

    public void additionalDealOut(Deck deck, Function<UserDto, Boolean> isYes, Consumer<UserDto> showResult) {
        playersInfo.forEach((player, bettingMoney) -> {
            while (player.isAvailableToDraw() && isYes.apply(UserDto.of(player))) {
                player.draw(deck);
                showResult.accept(UserDto.of(player));
            }
        });
    }

    public Map<Player, Integer> calculatePoint() {
        return playersInfo.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), Player::calculatePoint,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Player, Integer> calculateProfit(Dealer dealer) {
        return playersInfo.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> calculate(dealer, entry),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private int calculate(Dealer dealer, Map.Entry<Player, BettingMoney> entry) {
        return (int)(entry.getValue().getMoney() * entry.getKey().decideRatio(dealer).getRatio());
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(playersInfo.keySet());
    }
}
