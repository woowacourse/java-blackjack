package blackjack.controller;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, BlackJackInitManager blackJackInitManager) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck(),
                PlayersResults.create());
    }

    public void run() {
        List<String> names = InputView.readNames();

        Participants participants = blackJackInitManager.saveParticipants(names);

        giveStartingCards(participants);
        participants.getParticipants().forEach(this::giveMoreCard);

        List<PlayerResult> resultOfChallengers = getResultOfChallengers(participants);
        DealerResult resultOfDefender = getResultOfDefenders(participants);

        Participant defender = participants.findDefender();

        OutputView.printCardResult(resultOfChallengers, resultOfDefender, defender);
        OutputView.printGameResult(resultOfChallengers, resultOfDefender);
    }

    private void giveMoreCard(Participant participant) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant);
            return;
        }
        takeCardManually(participant);
    }

    private void takeCardsAsLongAsWanted(Participant participant) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackProcessManager.giveMoreCard(participant);
        OutputView.printCardResult(participant);

        if (gameRuleEvaluator.isBusted(participant)) {
            OutputView.printBustedParticipantWithName(participant);
            return;
        }

        if (participant.ableToTakeMoreCards()) {
            giveMoreCard(participant);
        }
    }

    private void takeCardManually(Participant participant) {
        while (participant.ableToTakeMoreCards()) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveMoreCard(participant);
        }
    }

    private void giveStartingCards(Participants participants) {
        blackjackProcessManager.giveStartingCards(participants);

        OutputView.printStartingCardsStatuses(participants);
    }

    private List<PlayerResult> getResultOfChallengers(Participants participants) {
        blackjackProcessManager.calculateChallengerResult(participants, gameRuleEvaluator);
        return blackjackProcessManager.getPlayersResult();
    }

    // TODO: 리스트로 받아야 하는지 고민해보기
    private DealerResult getResultOfDefenders(Participants participants) {
        Participant defender = participants.findDefender();
        return blackjackProcessManager.calculateDefenderResult(defender);
    }
}
