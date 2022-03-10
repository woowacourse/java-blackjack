package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.Status;
import blackjack.domain.player.Command;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    public void run() {
        Deck deck = new Deck();
        Game game = new Game(generatePlayers(deck), deck);
        printInitialStatus(game);

        executePlayerTurn(game);
        executeDealerTurn(game);

        printTotalScore(game);
        printResult(game);
    }

    private void printResult(Game game) {
        OutputView.printResult(game.getPlayerResults(), game.getDealerResult());
    }

    private Players generatePlayers(Deck deck) {
        try {
            return new Players(Arrays.stream(InputView.inputName())
                    .map(String::trim)
                    .map(name -> new Player(name, Cards.of(deck.initialDraw())))
                    .collect(Collectors.toList()));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generatePlayers(deck);
        }
    }

    private void executePlayerTurn(Game game) {
        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            Command command = inputCommand(name);
            game.playTurn(command);
            printStatus(game);
        }
    }

    private void executeDealerTurn(Game game) {
        while (game.doesDealerNeedToDraw()) {
            game.doDealerDraw();
            OutputView.printDealerHitMessage();
        }
    }

    private Command inputCommand(String name) {
        try {
            return InputView.requestHitOrStay(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand(name);
        }
    }

    private void printTotalScore(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();
        List<Status> result = createDealerStatus(dealer);
        addPlayerStatus(players, result);
        OutputView.printTotalScore(result);
    }

    private List<Status> createDealerStatus(Dealer dealer) {
        List<Status> result = new ArrayList<>();
        result.add(new Status(dealer.getName(),
                dealer.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                dealer.getScore()
        ));
        return result;
    }

    private void addPlayerStatus(List<Player> players, List<Status> result) {
        for (Player player : players) {
            result.add(new Status(player.getName(),
                    player.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                    player.getScore()
            ));
        }
    }

    private void printStatus(Game game) {
        Player player = game.getCurrentPlayer();
        OutputView.printStatus(
                new Status(
                        player.getName(),
                        player.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                        player.getScore()));
    }

    private void printInitialStatus(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Status> result = new ArrayList<>();
        result.add(new Status(dealer.getName(), List.of(CardDto.of(dealer.getCards().get(0))), dealer.getScore()));

        addPlayerStatus(players, result);

        OutputView.printInitialStatus(result);
    }
}
