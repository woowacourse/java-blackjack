package blackjack.controller;

import blackjack.domain.GameResult;
import blackjack.domain.Result;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String END_GAME_MARK = "n";

    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final List<Participant> participants = participantsSetUp();

        distributeCard(participants, cardDeck);
        showParticipantsName(participants);
        showDistributedCard(participants);
        playerGameProgress(new ArrayList<>(participants.subList(1, participants.size())), cardDeck);
        dealerGameProgress(participants.get(0), cardDeck);
        showFinalCardResult(participants);
        showGameResult(participants.get(0),
            new ArrayList<>(participants.subList(1, participants.size())), new GameResult());
    }

    private List<Participant> participantsSetUp() {
        final List<String> names = InputView.requestName();
        final List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer("딜러"));
        for (final String name : names) {
            participants.add(new Player(name));
        }
        return new ArrayList<>(participants);
    }

    private void distributeCard(final List<Participant> participants, final CardDeck cardDeck) {
        for (final Participant participant : participants) {
            participant.receiveCard(cardDeck.distribute());
            participant.receiveCard(cardDeck.distribute());
        }
    }

    private void showParticipantsName(final List<Participant> participants) {
        final String status = participants.stream()
            .map(Participant::getName)
            .filter(name -> !name.equals("딜러"))
            .collect(Collectors.joining(", "));
        OutputView.distributeMessage(status);
    }

    private void showDistributedCard(final List<Participant> participants) {
        for (final Participant participant : participants) {
            OutputView.showPlayerCard(participant.getName(), participant.showCards());
        }
    }

    private void playerGameProgress(final List<Participant> participants,
        final CardDeck cardDeck) {
        for (final Participant participant : participants) {
            singlePlayerGameProgress(cardDeck, participant);
        }
    }

    private void singlePlayerGameProgress(final CardDeck cardDeck, final Participant participant) {
        if (END_GAME_MARK.equals(askPlayerMoreCard(participant))) {
            OutputView.showPlayerCard(participant.getName(), participant.getPlayerCards());
            return;
        }
        participant.receiveCard(cardDeck.distribute());
        OutputView.showPlayerCard(participant.getName(), participant.getPlayerCards());
        if (isBust(participant)) {
            return;
        }
        singlePlayerGameProgress(cardDeck, participant);
    }

    private String askPlayerMoreCard(final Participant participant) {
        try {
            final String userInput = InputView.askMoreCard(participant.getName());
            return validateMoreCardOption(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return askPlayerMoreCard(participant);
        }
    }

    private String validateMoreCardOption(final String userInput) {
        if ("y".equals(userInput) || "n".equals(userInput)) {
            return userInput;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해야 합니다.");
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
        for (final Participant participant : participants) {
            OutputView.showCardResult(participant.getName(), participant.getPlayerCards(),
                participant.calculate());
        }
    }

    private void showGameResult(final Participant dealer, final List<Participant> players, final
    GameResult gameResult) {
        final int dealerWinCount = gameResult.calculateDealerWinCount(dealer, players);
        OutputView
            .showGameResult(dealer.getName(), dealerWinCount, players.size() - dealerWinCount);

        final Map<String, Result> results = gameResult.makePlayerResults(players, dealer);
        OutputView.showPlayerGameResult(results);
    }

}
