package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.result.Judge;
import blackjack.domain.result.ParticipantResults;

public class Application {

    public static void main(String[] args) {
        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
        ParticipantResults participantResults = new ParticipantResults();
        BlackjackController blackjackController = new BlackjackController(gameRuleEvaluator,
                new Judge(participantResults));

        blackjackController.run();
    }
}
