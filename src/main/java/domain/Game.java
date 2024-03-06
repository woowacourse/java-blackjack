package domain;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Cards cards;

    public Game(final List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
        cards = new Cards();
    }

    public CardsStatus start() {
        List<CardStatus> status = new ArrayList<>();
        for (Player player : players) {
            status.add(new CardStatus(player.getName(), pickTwoCards(player)));
        }
        return new CardsStatus(status);
    }

    private List<Card> pickTwoCards(final Player player) {
        player.add(cards.pick());
        player.add(cards.pick());
        return player.getCards();
    }

    public CardStatus pickOneCard(final String name) {
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        foundPlayer.saveCard(cards.pick());
        return new CardStatus(foundPlayer.getName(), foundPlayer.getCards());
    }


    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public CardStatus getCurrentCardStatus(final String name) {
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        return new CardStatus(foundPlayer.getName(), foundPlayer.getCards());
    }
}
