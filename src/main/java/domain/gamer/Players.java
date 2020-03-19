package domain.gamer;

import common.PlayerDto;
import domain.card.Deck;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    List<Player> players;

    public Players (List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void receivePlayerCards(Deck deck) {
        for (Player player : players) {
            while (!player.isBust() && InputView.inputGetMoreCard(player.getName()).equals("y")) {
                player.addCard(deck);
                OutputView.printGamerCardsState(PlayerDto.of(player));
            }
        }
    }

    public Player eachPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    public int participantNumber() {
        return players.size();
    }
}
