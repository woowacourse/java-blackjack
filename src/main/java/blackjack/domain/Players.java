package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> blackjackPlayers;

    public Players(List<String> playerNames) {
        this.blackjackPlayers = new ArrayList<>();
        blackjackPlayers.add(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.add(new Guest(playerName));
        }
    }

    public void startWithTwoCards(Cards cards) {
        for (Player blackjackPlayer : blackjackPlayers) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
    }

    public List<String> getNames() {
        return blackjackPlayers.stream().map(Player::getName).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return blackjackPlayers;
    }

    public List<Player> getGuests() {
        return blackjackPlayers.stream().filter(player -> !player.isDealer()).collect(Collectors.toList());
    }

    public Player getDealer() {
        return blackjackPlayers.stream().filter(Player::isDealer).findFirst()
                .orElseThrow(() -> new NoSuchElementException("딜러를 찾을 수 없습니다."));
    }
}
