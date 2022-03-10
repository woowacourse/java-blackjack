package blackjack;

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

public class Application {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Game game = new Game(generatePlayers(deck), deck);
        printInitialStatus(game);

        executePlayerTurn(game);
        executeDealerTurn(game);

        printTotalScore(game);
    }

    private static Players generatePlayers(Deck deck) {
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

    private static void executePlayerTurn(Game game) {
        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            Command command = inputCommand(name);
            game.playTurn(command);
            printStatus(game);
        }
    }

    private static void executeDealerTurn(Game game) {
        while (game.doesDealerNeedToDraw()) {
            game.doDealerDraw();
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    private static Command inputCommand(String name) {
        try {
            return InputView.requestHitOrStay(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand(name);
        }
    }

    private static void printTotalScore(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Status> result = new ArrayList<>();
        result.add(new Status(
                dealer.getName(),
                dealer.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                dealer.getScore()
        ));

        for (Player player : players) {
            result.add(new Status(
                    player.getName(),
                    player.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                    player.getScore()
            ));
        }
        OutputView.printTotalScore(result);
    }

    private static void printStatus(Game game) {
        Player player = game.getCurrentPlayer();
        OutputView.printStatus(
                new Status(
                        player.getName(),
                        player.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                        player.getScore()));
    }

    private static void printInitialStatus(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayersToList();

        List<Status> result = new ArrayList<>();
        result.add(new Status(
                dealer.getName(),
                List.of(CardDto.of(dealer.getCards().get(0))),
                dealer.getScore()
        ));

        for (Player player : players) {
            result.add(new Status(
                    player.getName(),
                    player.getCards().stream().map(CardDto::of).collect(Collectors.toList()),
                    player.getScore()
            ));
        }

        OutputView.printInitialStatus(result);
    }
}
