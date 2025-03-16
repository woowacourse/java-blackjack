package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

public class Judge {

    private final DealerResults dealerResults;
    private final PlayerResults playerResults;

    public Judge(DealerResults dealerResults, PlayerResults playerResults) {
        this.dealerResults = dealerResults;
        this.playerResults = playerResults;
    }

    public void calculateAllResults(Dealer dealer, Players players, GameRuleEvaluator gameRuleEvaluator) {
        for (Player player : players.getPlayers()) {
            calculateResult(dealer, player, gameRuleEvaluator);
        }
    }

    public void calculateResult(Dealer dealer, Player player) {
        Score dealerScore = new Score(dealer);
        Score playerScore = new Score(player);

        if (dealerScore.isBlackJack() || playerScore.isBlackJack()) {
            processOfBlackjack(player, dealerScore, playerScore);
            return;
        }

        if (dealerScore.isBusted() || playerScore.isBusted()) {
            processOfBusted(player, dealerScore, playerScore);
        }
    }

    private void processOfBusted(Player player, Score dealerScore, Score playerScore) {
        boolean isDealerBusted = dealerScore.isBusted();
        boolean isPlayerBusted = playerScore.isBusted();

        if (isDealerBusted && isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.TIE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.TIE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (isDealerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.LOSE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (isPlayerBusted) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.WIN, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }
    }

    private void processOfBlackjack(Player player, Score dealerScore, Score playerScore) {
        if (dealerScore.isBlackJack()) {
            if (playerScore.isBlackJack()) {
                PlayerResult playerResult = new PlayerResult(player, GameResultType.TIE, playerScore);
                DealerResult dealerResult = new DealerResult(GameResultType.TIE, dealerScore);
                dealerResults.add(player, dealerResult);
                playerResults.add(playerResult);
                return;
            }

            PlayerResult playerResult = new PlayerResult(player, GameResultType.LOSE, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.WIN, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
            return;
        }

        if (playerScore.isBlackJack()) {
            PlayerResult playerResult = new PlayerResult(player, GameResultType.WIN, playerScore);
            DealerResult dealerResult = new DealerResult(GameResultType.LOSE, dealerScore);
            dealerResults.add(player, dealerResult);
            playerResults.add(playerResult);
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

//        participantResults.add(challengerResult);
//        participantResults.add(defenderResult);
    }

//    public ParticipantResults getParticipantResults() {

    /// /        return participantResults;
//    }
    public DealerResults getDealerResults() {
        return dealerResults;
    }

    public PlayerResults getPlayerResults() {
        return playerResults;
    }
}
