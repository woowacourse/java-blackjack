package domain;

import domain.CardBox;
import domain.Player;
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

    public void playerDrawAddCard(final int index, final CardBox cardBox) {
        boolean flag = true;
        while (flag) {
            flag = !players.get(index).selectToPickOtherCard(cardBox);
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

    public String getNameOfPlayer(final int index) {
        return players.get(index).getName();
    }

    public List<String> getCardsOfPlayer(final int index) {
        return players.get(index).getCards();
    }

    public Player getPlayer(final int index) {
        return players.get(index);
    }

    public int getCardsSum(final int index) {
        return players.get(index).sumOfPlayerCards();
    }
}
