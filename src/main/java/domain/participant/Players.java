package domain.participant;

import domain.card.Cards;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("블랙잭은 최소 한명이상 가능합니다.");
        }
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public int size() {
        return players.size();
    }

    public Player getPlayer(final int index) {
        return players.get(index);
    }

    public int getCardsSum(final int index) {
        return players.get(index).sumOfCards();
    }

    public boolean isNotBurst(final int index) {
        return players.get(index).isNotBurst();
    }

    public List<Cards> getCards() {
        return players.stream()
                .map(Player::getCards)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<List<String>> cardsToString() {
        return players.stream()
                .map(Player::getCards)
                .map(Cards::cardsToString)
                .collect(Collectors.toList());
    }

    public List<Integer> getMonies() {
        return players.stream()
                .map(Player::getMoney)
                .collect(Collectors.toList());
    }
}
