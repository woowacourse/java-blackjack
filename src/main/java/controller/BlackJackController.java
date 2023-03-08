package controller;

import domain.game.BlackJack;
import domain.game.Deck;
import domain.participant.Player;
import domain.participant.Players;
import template.Repeater;
import domain.participant.Name;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJack blackJack;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJack = Repeater.repeat(this::createBlackJack);
    }

    private BlackJack createBlackJack() {
        final List<Name> playerNames = userNameRequest();
        final List<Integer> bets = new ArrayList<>();
        for (Name playerName : playerNames) {
            bets.add(inputView.betRequest(playerName));
        }
        return BlackJack.getInstance(playerNames, bets, new Deck());
    }

    public void process() {
        outputView.printInitialStatus(blackJack.getDealer(), blackJack.getPlayers());
        getPlayersDecision();
        getDealerResult();
        outputView.printStatus(blackJack.getDealer(), blackJack.getPlayers());
        outputView.printFinalResult(blackJack.getDealer(), blackJack.getGameResult());
    }

    private List<Name> userNameRequest() {
        return inputView.userNameRequest()
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void getPlayersDecision() {
        final Players participants = blackJack.getPlayers();
        for (Player participant : participants.getPlayers()) {
            getPlayerDecision(participant);
        }
    }

    private void getPlayerDecision(final Player player) {
        while (!blackJack.isBusted(player) && wantMoreCard(player)) {
            blackJack.drawCard(player);
            outputView.printPlayerCards(player);
        }
    }

    private boolean wantMoreCard(final Player player) {
        return Repeater.repeat(() -> cardRequest(player.getName()));
    }

    private boolean cardRequest(Name name) {
        return inputView.cardRequest(name);
    }

    private void getDealerResult() {
        final int cardCount = blackJack.getAdditionalCardCount();
        if (cardCount == 0) {
            outputView.printAdditionalCardCount(cardCount, false);
            return;
        }
        outputView.printAdditionalCardCount(cardCount, true);
    }
}
