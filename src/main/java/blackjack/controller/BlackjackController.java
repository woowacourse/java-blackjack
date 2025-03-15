package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Participants;
import blackjack.domain.game.Player;
import blackjack.domain.result.Judge;
import blackjack.domain.result.ParticipantResults;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final Judge judge;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, Judge judge) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.judge = judge;
    }

    public void run() {
        List<String> names = InputView.readNames();
        Participants participants = saveParticipants(names);

        BlackjackGame blackjackGame = new BlackjackGame(new Deck(), participants);
        giveStartingCards(blackjackGame);

        participants.getParticipants().forEach(participant -> giveMoreCard(participant, blackjackGame));

        ParticipantResults participantResults = calculateResultOfParticipants(participants);

        OutputView.printCardResult(participantResults);
        OutputView.printGameResult(participantResults);
    }

    private void giveMoreCard(Participant participant, BlackjackGame blackjackGame) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant, blackjackGame);
            return;
        }
        takeCardManually(participant, blackjackGame);
    }

    private void takeCardsAsLongAsWanted(Participant participant, BlackjackGame blackjackGame) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackGame.giveMoreCard(participant);
        OutputView.printCardResult(participant);

        if (gameRuleEvaluator.isBusted(participant)) {
            OutputView.printBustedParticipantWithName(participant);
            return;
        }

        if (participant.ableToTakeMoreCards()) {
            giveMoreCard(participant, blackjackGame);
        }
    }

    private void takeCardManually(Participant participant, BlackjackGame blackjackGame) {
        while (participant.ableToTakeMoreCards()) {
            OutputView.printMoreCard();
            blackjackGame.giveMoreCard(participant);
        }
    }


    private void giveStartingCards(BlackjackGame blackjackGame) {
        blackjackGame.giveStartingCards();

        OutputView.printStartingCardsStatuses(blackjackGame.getParticipants());
    }

    private ParticipantResults calculateResultOfParticipants(Participants participants) {
        judge.calculateAllResults(participants, gameRuleEvaluator);
        return judge.getParticipantResults();
    }

    private Participants saveParticipants(List<String> names) {
        List<Participant> participants = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());
        participants.add(new Dealer(new Hand()));
        return new Participants(participants);
    }
}
