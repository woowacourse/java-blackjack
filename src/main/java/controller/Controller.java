package controller;

import domain.*;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Deck deck;

    public Controller(final CardGenerator cardGenerator) {
        this.deck = Deck.from(cardGenerator);
    }

    public void run() {
        final List<String> playerNames = InputView.readPlayerNames();
        OutputView.printSetupGame(playerNames);

        List<Participant> participants = playerNames.stream()
                .map(Name::from)
                .map(Participant::from)
                .collect(Collectors.toList());

        Dealer dealer = Dealer.create();

        dealer.takeCard(deck.drawCard());

        participants.forEach(participant -> {
            participant.takeCard(deck.drawCard());
            participant.takeCard(deck.drawCard());
        });

        OutputView.printPlayerCards(dealer.getName(), dealer.displayCards());
        participants.forEach(participant -> OutputView.printPlayerCards(participant.getName(), participant.displayCards()));

        for (Participant participant : participants) {
            drawCard(participant);
        }

        int score = dealer.getScore();
        while (score < 17) {
            OutputView.printHitOrStay(score);
            dealer.takeCard(deck.drawCard());
            score = dealer.getScore();
        }
        OutputView.printHitOrStay(score);

        OutputView.printPlayerScore(dealer.getName(), dealer.displayCards(), dealer.getScore());

        for (Participant participant : participants) {
            OutputView.printPlayerScore(participant.getName(), participant.displayCards(), participant.getScore());
        }

        final Results results = Results.of(dealer.getScore(), participants);
        OutputView.printGameResult(results);
    }

    private void drawCard(final Participant participant) {
        int score = participant.getScore();
        while (score <= 21) {
            final Command command = InputView.readCommand(participant.getName().getName());
            if (!command.isValue()) {
                break;
            }
            participant.takeCard(deck.drawCard());
            score = participant.getScore();
            OutputView.printPlayerCards(participant.getName(), participant.displayCards());
        }
    }


}
