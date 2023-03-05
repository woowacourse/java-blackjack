package controller;

import domain.BlackJack;
import domain.Deck;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import template.Repeater;
import domain.participant.Name;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJack blackJack;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJack = BlackJack.getInstance(userNameRequest(), new Deck());
    }

    public void process() {
        outputView.printInitialStatus(blackJack.getDealer(), blackJack.getPlayers());
        getPlayersDecision();
//        getDealerResult();
//        outputView.printStatus(blackJack.getDealerStatus(), blackJack.getUsersStatus());
//        outputView.printFinalResult(blackJack.getGameResult());
    }

    private List<Name> userNameRequest() {
        final List<String> strings = Repeater.repeat(inputView::userNameRequest);
        return strings
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void getPlayersDecision() {
        final Players participants = blackJack.getPlayers();
        for (Participant participant : participants.getPlayers()) {
            if (participant instanceof Player) {
                getPlayerDecision(participant);
            }
        }
    }

    private void getPlayerDecision(final Participant player) {
        boolean decision = Repeater.repeat(() -> cardRequest(player.getName()));
        while (decision && !blackJack.isBusted(player)) {
            blackJack.drawCard(player);
//            outputView.printCardsOf(player, blackJack.getCardsFrom(player));
            decision = Repeater.repeat(() -> cardRequest(player.getName()));
        }
    }

    private boolean cardRequest(Name name) {
        return inputView.cardRequest(name);
    }

    /*private void getDealerResult() {
        final int cardCount = blackJack.finalizeDealer();
        outputView.printAdditionalCardCountOfDealer(cardCount);
    }*/
}
