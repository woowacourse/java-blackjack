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

import static java.util.stream.Collectors.toList;

public class GameController {

    public void run() {
        Game game = generateGame();
        printInitialStatus(game);

        executePlayerTurn(game);
        executeDealerTurn(game);

        printTotalScore(game);
        printResult(game);
    }

    private Game generateGame() {
        try {
            return new Game(inputPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateGame();
        }
    }

    private List<String> inputPlayerNames() {
        return Arrays.stream(InputView.inputName())
                .map(String::trim)
                .collect(toList());
    }

    private void printResult(Game game) {
        OutputView.printResult(game.getPlayerResults(), game.getDealerResult());
    }

    private Players generatePlayers(Deck deck) {
        try {
            return new Players(Arrays.stream(InputView.inputName())
                    .map(String::trim)
                    .map(name -> new Player(name, Cards.of(deck.initialDraw())))
                    .collect(toList()));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generatePlayers(deck);
        }
    }

    private void executePlayerTurn(Game game) {
        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            Command command = inputCommand(name);
            Status status = game.playTurn(command);
            OutputView.printStatus(status);
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
                dealer.getCardsToList().stream().map(CardDto::of).collect(toList()),
                dealer.getScore()
        ));
        return result;
    }

    private void addPlayerStatus(List<Player> players, List<Status> result) {
        for (Player player : players) {
            result.add(new Status(player.getName(),
                    player.getCardsToList().stream().map(CardDto::of).collect(toList()),
                    player.getScore()
            ));
        }
    }

    private void printInitialStatus(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Status> result = new ArrayList<>();
        result.add(new Status(dealer.getName(), List.of(CardDto.of(dealer.getCardsToList().get(0))), dealer.getScore()));

        addPlayerStatus(players, result);
        OutputView.printInitialStatus(result);
    }
}
