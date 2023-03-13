package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.Map;

public final class ResultGame {

    private final Map<Participant, Betting> resultBetting;

    private ResultGame(final Map<Participant, Betting> resultBetting) {
        this.resultBetting = resultBetting;
    }

    public static ResultGame from(final Map<Participant, Betting> resultBetting) {
        return new ResultGame(resultBetting);
    }

    public void betMoney(final Participant participant, final Betting betting) {
        resultBetting.put(participant, betting);
    }

    public void calculateResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        final Score dealerScore = dealer.getScore();
        for (final Participant player : players) {
            final Betting bettingMoney = resultBetting.get(player);
            final ResultState playerGameResult = ResultState.resultPlayer(player.getScore(), dealerScore);

            resultBetting.put(player, Betting.from(playerGameResult.calculateProfit(bettingMoney)));
        }
    }

    public Betting getDealerResult() {
        final int playerAllMoney = resultBetting.values().stream()
                .map(Betting::getValue)
                .reduce(0, Integer::sum);

        return Betting.from(-playerAllMoney);
    }

    public Betting getPlayerResult(final Participant player) {
        return resultBetting.get(player);
    }
}
