package blackjack;

import blackjack.domain.Game;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.Status;
import blackjack.domain.player.Command;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        String[] names = InputView.inputName();
        Deck deck = new Deck();
        List<Player> players = Arrays.stream(names)
                .map(n -> new Player(n, Cards.of(deck.initialDraw())))
                .collect(Collectors.toList());

        Game game = new Game(players, deck);
        printInitialStatus(game);

        while (game.isPossibleToPlay()) {
            String name = game.getCurrentHitablePlayerName();
            Command command = InputView.requestHitOrStay(name);
            game.playTurn(command);
            printStatus(game);
        }

        while (game.doesNeedToDraw()) {
            game.doDealerDraw();
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }

        printTotalScore(game);
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
