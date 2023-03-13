package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.Map;

public final class ResultGame {

    private final Map<Participant, Integer> resultBetting;

    private ResultGame(final Map<Participant, Integer> resultBetting) {
        this.resultBetting = resultBetting;
    }

    public static ResultGame from(final Map<Participant, Integer> resultBetting) {
        return new ResultGame(resultBetting);
    }

    public void betMoney(final Participant participant, final Betting betting) {
        resultBetting.put(participant, betting.getValue());
    }

    public void calculateResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        final Score dealerScore = dealer.getScore();
        for (final Participant player : players) {
            final int bettingMoney = resultBetting.get(player);
            final ResultState playerGameResult = ResultState.resultPlayer(player.getScore(), dealerScore);

            resultBetting.put(player, playerGameResult.calculateProfit(bettingMoney));
        }
    }

    public int getDealerResult() {
        final int playerAllMoney = resultBetting.values().stream()
                .reduce(0, java.lang.Integer::sum);

        return -playerAllMoney;
    }

    public int getPlayerResult(final Participant player) {
        return resultBetting.get(player);
    }
}
