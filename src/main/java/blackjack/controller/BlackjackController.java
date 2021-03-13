package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Names;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final Names names = requestName();
        final BlackjackGame blackjackGame = new BlackjackGame(names, requestMoneys(names));
        blackjackGame.distributeCard();
        OutputView.showNameAndCardInfo(blackjackGame.getParticipants());

        gameProgress(blackjackGame.getParticipants());
        OutputView.showCardsResult(blackjackGame.getParticipants().getParticipantGroup());
        OutputView.showGameResult(blackjackGame.makeParticipantResults().getResults());
    }

    private Names requestName() {
        try {
            return validateName();
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return requestName();
        }
    }

    private Names validateName() {
        final List<Name> names = InputView.requestName().stream()
            .map(Name::new)
            .collect(Collectors.toList());
        return new Names(names);
    }

    private List<Double> requestMoneys(final Names names) {
        final List<Double> moneys = new ArrayList<>();
        for (final Name name : names.toList()) {
            final double value = InputView.requestMoney(name);
            moneys.add(value);
        }
        return moneys;
    }

    private void gameProgress(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();
        for (final Participant player : players) {
            singlePlayerGameProgress(player);
        }
        dealerGameProgress(dealer);
    }

    private void singlePlayerGameProgress(final Participant player) {
        while (!player.isBust() && InputView.askMoreCard(player.getName())) {
            player.receiveCard(CardDeck.distribute());
            OutputView.showParticipantCard(player, player.getPlayerCards());
        }
        if (player.isBust()) {
            OutputView.bustMessage();
            return;
        }
        OutputView.showParticipantCard(player, player.getPlayerCards());
    }

    private void dealerGameProgress(final Participant dealer) {
        while (dealer.checkMoreCardAvailable()) {
            OutputView.dealerMoreCard();
            dealer.receiveCard(CardDeck.distribute());
        }
    }
}
