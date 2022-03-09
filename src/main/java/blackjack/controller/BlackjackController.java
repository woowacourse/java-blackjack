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

        for (Player player : players) {
            if (player.getName().equals("딜러")) {
                continue;
            }
            while (!player.isOverLimit(21)) {
                if (InputView.requestMoreCard(player.getName()).equals("y")) {
                    blackjackGame.addCard(player);
                    List<GameResponse> gameResponses = new ArrayList<>();
                    GameResponse gameResponse = new GameResponse(player.getName(), player.getDeck());
                    gameResponses.add(gameResponse);
                    OutputView.announcePresentCards(gameResponses);
                    continue;
                }
                break;
            }
        }
        Player dealer = players.get(0);
        announceDealerCanGetMoreCard(blackjackGame, dealer);
    }

    private void announceDealerCanGetMoreCard(BlackjackGame blackjackGame, Player dealer) {
        if (!dealer.isOverLimit(16)) {
            OutputView.announceDealerGetMoreCard();
            blackjackGame.addCard(dealer);
            return;
        }
        OutputView.announceDealerStopMoreCard();
    }

    private List<GameResponse> toResponse(List<Player> players) {
        List<GameResponse> gameResponses = new ArrayList<>();
        for (Player player : players) {
            gameResponses.add(new GameResponse(player.getName(), player.getDeck()));
        }
        return gameResponses;
    }
}
