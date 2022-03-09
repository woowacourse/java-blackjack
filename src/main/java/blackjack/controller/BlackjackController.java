package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.GameResponse;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void playGame() {

        List<String> playerNames = InputView.inputPlayerNames();

        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        List<Player> players = blackjackGame.initGames();

        OutputView.announceStartGame(players.stream().map(Player::getName).collect(Collectors.toList()));
        OutputView.announcePresentCards(toResponse(players));
    }

    private List<GameResponse> toResponse(List<Player> players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }
}
