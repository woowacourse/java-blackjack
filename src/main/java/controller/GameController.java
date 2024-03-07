package controller;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import controller.dto.GameResult;
import controller.dto.PlayerResult;
import domain.Dealer;
import domain.Game;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = start();
        if (game.isNotDealerBlackJack()) {
            startRound(game);
            giveCardToDealer(game);
        }
        List<Integer> scores = new ArrayList<>();
        scores.add(game.getParticipant().dealer().calculateScore());
        for (Player player : game.getParticipant().players()) {
            scores.add(player.calculateScore());
        }
        outputView.printResult(getCurrentCardsStatus(game), scores);
        outputView.printGameResult(getResults(game));
    }

    private Game start() {
        List<String> names = inputView.enterPlayerNames();
        Dealer dealer = new Dealer("딜러");

        Game game = new Game(dealer, names);
        outputView.printAfterStartGame(game.initiateGameCondition());
        return game;
    }

    private CardsStatus getCurrentCardsStatus(final Game game) {
        List<CardStatus> cardStatuses = new ArrayList<>();

        Participant participant = game.getParticipant();
        Dealer dealer = participant.dealer();
        List<Player> players = participant.players();

        cardStatuses.add(new CardStatus(dealer.getName(), dealer.getCards()));
        for (Player player : players) {
            cardStatuses.add(new CardStatus(player.getName(), player.getCards()));
        }

        return new CardsStatus(cardStatuses);
    }

    private void startRound(final Game game) {
        List<String> names = game.getPlayerNames();
        for (String name : names) {
            String command = inputView.decideToGetMoreCard(name);
            CardStatus status = game.getCurrentCardStatus(name);
            while ("y".equals(command)) {
                status = game.pickOneCard(name);
                outputView.printCardStatus(status);
                command = inputView.decideToGetMoreCard(name);
            }
            outputView.printCardStatus(status);
        }
    }

    private void giveCardToDealer(final Game game) {
        int count = game.giveCardsToDealer();
        outputView.printDealerPickMessage(count);
    }

    public GameResult getResults(final Game game) {
        List<Boolean> results = game.judge();
        List<String> names = game.getPlayerNames();
        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals("딜러")) {
                continue;
            }
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }
}
