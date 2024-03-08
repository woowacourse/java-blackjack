package controller;

import controller.dto.GameResult;
import controller.dto.HandStatus;
import controller.dto.PlayerResult;
import domain.Dealer;
import domain.GameRule;
import domain.Participant;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Round round = start();
        startRound(round);
        giveCardToDealer(round);

        List<Integer> scores = new ArrayList<>();
        scores.add(getDealer(round).calculateResultScore());
        for (Player player : round.getParticipant().players()) {
            scores.add(player.calculateResultScore());
        }
        outputView.printResult(getCurrentCardsStatus(round), scores);
        outputView.printGameResult(getResults(round));
    }

    private Dealer getDealer(final Round round) {
        return round.getParticipant().dealer();
    }

    private Round start() {
        List<String> names = inputView.enterPlayerNames();
        Round round = new Round(names);
        outputView.printAfterStartGame(round.initiateGameCondition());
        return round;
    }

    private List<HandStatus> getCurrentCardsStatus(final Round round) {
        List<HandStatus> handStatuses = new ArrayList<>();

        Participant participant = round.getParticipant();
        Dealer dealer = participant.dealer();
        List<Player> players = participant.players();

        handStatuses.add(new HandStatus(dealer.getName(), dealer.getHand()));
        for (Player player : players) {
            handStatuses.add(new HandStatus(player.getName(), player.getHand()));
        }

        return handStatuses;
    }

    private void startRound(final Round round) {
        List<String> names = round.getPlayerNames();
        for (String name : names) {
            round.giveCardToPlayer(name, outputView, inputView);
        }
    }

    private void giveCardToDealer(final Round round) {
        int count = round.giveCardsToDealer();
        outputView.printDealerPickMessage(count);
    }

    public GameResult getResults(final Round round) {
        GameRule rule = new GameRule(round.getParticipant());
        List<Boolean> results = rule.judge();
        List<String> names = round.getPlayerNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }
}
