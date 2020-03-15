package domain.gamer;

import common.PlayerDto;
import domain.card.Deck;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    List<Player> players;

    public Players (List<String> playersName, Deck deck) {
        List<Player> players = playersName.stream()
                .map(name -> new Player(deck.getInitCards(), name))
                .collect(Collectors.toList()) ;
        this.players = players;
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
