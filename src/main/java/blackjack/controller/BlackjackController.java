package blackjack.controller;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.Select;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Names;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final List<Participant> participants = participantsSetUp();
        final List<Participant> players = new ArrayList<>(
            participants.subList(1, participants.size()));

        distributeCard(participants, cardDeck);
        showParticipantsName(players);
        showDistributedCard(participants);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(participants.get(0), cardDeck);
        showFinalCardResult(participants);
        showGameResult(participants.get(0), players, new GameResult());
    }

    private List<Participant> participantsSetUp() {
        final Names names = requestName();
        final List<Participant> participants = names.toList().stream()
            .map(Player::new)
            .collect(Collectors.toList());
        participants.add(0, new Dealer());
        return new ArrayList<>(participants);
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

    private void distributeCard(final List<Participant> participants, final CardDeck cardDeck) {
        for (final Participant participant : participants) {
            participant.receiveCard(cardDeck.distribute());
            participant.receiveCard(cardDeck.distribute());
        }
    }

    private void showParticipantsName(final List<Participant> players) {
        OutputView.distributeMessage(players);
    }

    private void showDistributedCard(final List<Participant> participants) {
        OutputView.showParticipantsCard(participants);
    }

    private void playerGameProgress(final List<Participant> participants,
        final CardDeck cardDeck) {
        for (final Participant participant : participants) {
            singlePlayerGameProgress(cardDeck, participant);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Participant participant) {
        if (askPlayerMoreCard(participant)) {
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

    private boolean askPlayerMoreCard(final Participant participant) {
        try {
            final String userInput = InputView.askMoreCard(participant.getName());
            return validateMoreCardOption(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return askPlayerMoreCard(participant);
        }
    }

    private boolean validateMoreCardOption(final String userInput) {
        return new Select(userInput).getIsMoreCard();
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

    private void showFinalCardResult(final List<Participant> participants) {
        OutputView.displayNewLine();
        OutputView.showCardsResult(participants);
    }

    private void showGameResult(final Participant dealer, final List<Participant> players,
        final GameResult gameResult) {
        OutputView.showGameResult(dealer, players, gameResult);
        final Map<Name, Result> results = gameResult.makePlayerResults(players, dealer);
        OutputView.showPlayerGameResult(results);
    }

}
