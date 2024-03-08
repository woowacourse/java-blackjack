package controller;

import static domain.constants.CardCommand.HIT;
import static domain.constants.CardCommand.STAND;

import controller.dto.GameResult;
import controller.dto.HandStatus;
import controller.dto.PlayerResult;
import domain.Dealer;
import domain.Deck;
import domain.GameRule;
import domain.Participant;
import domain.Player;
import domain.constants.CardCommand;
import domain.constants.Outcome;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Round {
    private static final int THRESHOLD = 16;

    private final Participant participant;
    private final Deck deck;

    public Round(final Participant participant, final Deck deck) {
        this.participant = participant;
        this.deck = deck;
    }

    public static Round from(final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        return new Round(
                new Participant(new Dealer(), players),
                new Deck()
        );
    }

    public List<HandStatus> initiateGameCondition() {
        List<HandStatus> status = new ArrayList<>();

        Dealer dealer = participant.dealer();
        dealer.pickTwoCards(deck);
        status.add(new HandStatus(dealer.getName(), dealer.getHand()));

        for (Player player : participant.players()) {
            player.pickTwoCards(deck);
            status.add(new HandStatus(player.getName(), player.getHand()));
        }
        return status;
    }

    public void giveCardToPlayer(final String name, final OutputView outputView, final InputView inputView) {
        Player player = getPlayer(name);
        HandStatus currentHand = new HandStatus(player.getName(), player.getHand());
        CardCommand command = inputCommand(name, inputView);
        while (HIT.equals(command)) {
            currentHand = createHandStatusAfterPick(player);
            outputView.printCardStatus(currentHand);
            if (player.cannotDraw()) {
                break;
            }
            command = inputCommand(name, inputView);
        }
        if (STAND.equals(command)) {
            outputView.printCardStatus(currentHand);
        }
    }

    private HandStatus createHandStatusAfterPick(final Player player) {
        player.pickOneCard(deck);
        return new HandStatus(player.getName(), player.getHand());
    }

    private CardCommand inputCommand(final String name, final InputView inputView) {
        return CardCommand.from(inputView.decideToGetMoreCard(name));
    }

    public void finish(final OutputView outputView) {
        List<Integer> scores = new ArrayList<>();
        scores.add(participant.dealer().calculateResultScore());
        for (Player player : participant.players()) {
            scores.add(player.calculateResultScore());
        }
        outputView.printResult(getCurrentHandStatus(), scores);
        outputView.printGameResult(getGameResult());
    }

    public List<HandStatus> getCurrentHandStatus() {
        List<HandStatus> handStatuses = new ArrayList<>();
        handStatuses.add(new HandStatus(participant.dealer().getName(), participant.dealer().getHand()));
        for (Player player : participant.players()) {
            handStatuses.add(new HandStatus(player.getName(), player.getHand()));
        }
        return handStatuses;
    }

    public GameResult getGameResult() {
        GameRule rule = new GameRule(participant);
        List<Outcome> results = rule.judge();
        List<String> names = getPlayerNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }

    public List<String> getPlayerNames() {
        List<Player> players = participant.players();
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public int giveCardsToDealer() {
        Dealer dealer = participant.dealer();

        int currentScore = dealer.calculateResultScore();

        int count = 0;
        while (currentScore <= THRESHOLD) {
            dealer.pickOneCard(deck);
            currentScore = dealer.calculateResultScore();
            count++;
        }
        return count;
    }

    public Player getPlayer(final String name) {
        return participant.players()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
