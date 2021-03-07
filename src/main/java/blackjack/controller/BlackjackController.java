package blackjack.controller;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Names;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Participants participants = new Participants(requestName());
        participants.distributeCard(cardDeck);
        OutputView.showNameAndCardInfo(participants);

        gameProgress(participants.getPlayers(), participants.getDealer(), cardDeck);
        OutputView.showCardsResult(participants.getParticipantGroup());
        showGameResult(participants.getDealer(), participants.getPlayers());
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

    private void gameProgress(final List<Participant> participants, final Participant dealer,
        final CardDeck cardDeck) {
        for (final Participant participant : participants) {
            singlePlayerGameProgress(cardDeck, participant);
        }
        dealerGameProgress(dealer, cardDeck);
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Participant participant) {
        if (!InputView.askMoreCard(participant.getName())) {
            OutputView.showParticipantCard(participant);
            return;
        }
        participant.receiveCard(cardDeck.distribute());
        OutputView.showParticipantCard(participant);
        if (isBust(participant)) {
            return;
        }
        singlePlayerGameProgress(cardDeck, participant);
    }

    private boolean isBust(final Participant participant) {
        if (participant.isBust()) {
            OutputView.bustMessage();
            return true;
        }
        return false;
    }

    private void dealerGameProgress(final Participant dealer, final CardDeck cardDeck) {
        while (dealer.checkMoreCardAvailable()) {
            OutputView.dealerMoreCard();
            dealer.receiveCard(cardDeck.distribute());
        }
    }

    private void showGameResult(final Participant dealer, final List<Participant> players) {
        final GameResult gameResult = new GameResult();
        OutputView.showGameResult(dealer, players, gameResult);
        final Map<Name, Result> results = gameResult.makePlayerResults(players, dealer);
        OutputView.showPlayerGameResult(results);
    }

}
