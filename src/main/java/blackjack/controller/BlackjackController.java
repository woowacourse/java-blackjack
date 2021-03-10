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
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final Participants participants = createParticipants();
        participants.distributeCard();
        OutputView.showNameAndCardInfo(participants);

        gameProgress(participants.getPlayers(), participants.getDealer());
        OutputView.showCardsResult(participants.getParticipantGroup());

        final Map<Name, Integer> participantResults = new BlackjackGame()
            .makeParticipantResults(participants.getDealer(), participants.getPlayers());
        OutputView.showGameResult(participantResults);
    }

    private Participants createParticipants() {
        final Names names = requestName();
        final List<Double> moneys = new ArrayList<>();
        for (final Name name : names.toList()) {
            final double value = InputView.requestMoney(name);
            moneys.add(value);
        }
        return new Participants(names, new ArrayList<>(moneys));
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

    private void gameProgress(final List<Participant> participants, final Participant dealer) {
        for (final Participant participant : participants) {
            singlePlayerGameProgress(participant);
        }
        dealerGameProgress(dealer);
    }

    private void singlePlayerGameProgress(final Participant participant) {
        while (!participant.isBust() && InputView.askMoreCard(participant.getName())) {
            participant.receiveCard(CardDeck.distribute());
            OutputView.showParticipantCard(participant, participant.getPlayerCards());
        }
        if (participant.isBust()) {
            OutputView.bustMessage();
            return;
        }
        OutputView.showParticipantCard(participant, participant.getPlayerCards());
    }

    private void dealerGameProgress(final Participant dealer) {
        while (dealer.checkMoreCardAvailable()) {
            OutputView.dealerMoreCard();
            dealer.receiveCard(CardDeck.distribute());
        }
    }

}
