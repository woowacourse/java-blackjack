package domain;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Cards cards;

    public Game(final Player... players) {
        this.players = Arrays.asList(players);
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
}
