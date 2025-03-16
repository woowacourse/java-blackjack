package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

public class Judge {
    private final ParticipantResults participantResults;

    public Judge(ParticipantResults participantResults) {
        this.participantResults = participantResults;
    }

    public void calculateAllResults(Dealer dealer, Players players, GameRuleEvaluator gameRuleEvaluator) {
        for (Player player : players.getPlayers()) {
            calculateResult(dealer, player, gameRuleEvaluator);
        }
    }

    private void calculateResult(Dealer dealer, Player player, GameRuleEvaluator gameRuleEvaluator) {
        boolean isDefenderBusted = gameRuleEvaluator.isBusted(dealer);
        boolean isChallengerBusted = gameRuleEvaluator.isBusted(player);

        int challengerValue = player.getOptimisticValue();
        int defenderValue = dealer.getOptimisticValue();

        if (isDefenderBusted) {
            processWhenDefenderIsBusted(player, dealer, isChallengerBusted, challengerValue, defenderValue);
            return;
        }

        if (isChallengerBusted) {
            saveResult(player, dealer, GameResultType.LOSE, challengerValue, defenderValue);
            return;
        }

        GameResultType resultOfChallenger = GameResultType.find(challengerValue, defenderValue);
        saveResult(player, dealer, resultOfChallenger, challengerValue, defenderValue);
    }

    private void processWhenDefenderIsBusted(Player player, Dealer dealer, boolean isBustedChallenger,
                                             int challengerValue, int defenderValue) {
        if (isBustedChallenger) {
            saveResult(player, dealer, GameResultType.TIE, challengerValue, defenderValue);
            return;
        }

        saveResult(player, dealer, GameResultType.WIN, challengerValue, defenderValue);
    }

    private void saveResult(Player player, Dealer dealer, GameResultType resultTypeOfChallenger,
                            int challengerValue, int defenderValue) {
        GameResultType resultTypeOfDefender = resultTypeOfChallenger.getOppositeType();

        ParticipantResult challengerResult = new ParticipantResult(player, resultTypeOfChallenger, challengerValue);
        ParticipantResult defenderResult = new ParticipantResult(dealer, resultTypeOfDefender,
                defenderValue);

        participantResults.add(challengerResult);
        participantResults.add(defenderResult);
    }

    public ParticipantResults getParticipantResults() {
        return participantResults;
    }
}
