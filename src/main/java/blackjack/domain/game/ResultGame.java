package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.ResultDto;

import java.util.Map;

public final class ResultGame {

    private final Map<Participant, Betting> resultBetting;

    private ResultGame(final Map<Participant, Betting> resultBetting) {
        this.resultBetting = resultBetting;
    }

    public static ResultGame of(final BettingTable table, final Participants participants) {
        Map<Participant, Betting> bettingTable = table.getTable();
        calculateResult(bettingTable, participants);
        return new ResultGame(bettingTable);
    }

    public void betMoney(final Participant participant, final Betting betting) {
        resultBetting.put(participant, betting);
    }

    private static void calculateResult(final Map<Participant, Betting> table, final Participants participants) {
        final Participant dealer = participants.getDealer();

        for (final Participant player : participants.getPlayers()) {
            final ResultState playerState = ResultState.getState(
                    ResultDto.from(player),
                    ResultDto.from(dealer));

            final Betting money = table.get(player);
            table.put(player, playerState.calculateProfit(money.getValue()));
        }
    }

    public Betting getDealerResult() {
        final int playerAllMoney = resultBetting.values().stream()
                .mapToInt(Betting::getValue)
                .sum();

        return Betting.from(-playerAllMoney);
    }

    public Betting getPlayerResult(final Participant player) {
        return resultBetting.get(player);
    }
}
