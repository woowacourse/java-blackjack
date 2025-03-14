package blackjack.domain.result;

import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Participants;

public class Judge {
    private final ParticipantResults participantResults;

    public Judge(ParticipantResults participantResults) {
        this.participantResults = participantResults;
    }

    public void calculateAllResults(Participants participants, GameRuleEvaluator gameRuleEvaluator) {
        Participant defender = participants.findDefender();

        for (Participant participant : participants.getParticipants()) {
            if (participant.equals(defender)) {
                continue;
            }
            calculateResult(defender, participant, gameRuleEvaluator);
        }
    }

    private void calculateResult(Participant defender, Participant challenger, GameRuleEvaluator gameRuleEvaluator) {
        boolean isDefenderBusted = gameRuleEvaluator.isBusted(defender);
        boolean isChallengerBusted = gameRuleEvaluator.isBusted(challenger);

        int challengerValue = challenger.getOptimisticValue();
        int defenderValue = defender.getOptimisticValue();

        if (isDefenderBusted) {
            processWhenDefenderIsBusted(challenger, defender, isChallengerBusted, challengerValue, defenderValue);
            return;
        }

        if (isChallengerBusted) {
            saveResult(challenger, defender, GameResultType.LOSE, challengerValue, defenderValue);
            return;
        }

        GameResultType resultOfChallenger = GameResultType.find(challengerValue, defenderValue);
        saveResult(challenger, defender, resultOfChallenger, challengerValue, defenderValue);
    }

    private void processWhenDefenderIsBusted(Participant challenger, Participant defender, boolean isBustedChallenger,
                                             int challengerValue, int defenderValue) {
        if (isBustedChallenger) {
            saveResult(challenger, defender, GameResultType.TIE, challengerValue, defenderValue);
            return;
        }

        saveResult(challenger, defender, GameResultType.WIN, challengerValue, defenderValue);
    }

    private void saveResult(Participant challenger, Participant defender, GameResultType resultTypeOfChallenger,
                            int challengerValue, int defenderValue) {
        GameResultType resultTypeOfDefender = resultTypeOfChallenger.getOppositeType();

        ParticipantResult challengerResult = new ParticipantResult(challenger, resultTypeOfChallenger, challengerValue);
        ParticipantResult defenderResult = new ParticipantResult(defender, resultTypeOfDefender,
                defenderValue);

        participantResults.add(challengerResult);
        participantResults.add(defenderResult);
    }

    public ParticipantResults getParticipantResults() {
        return participantResults;
    }
}
