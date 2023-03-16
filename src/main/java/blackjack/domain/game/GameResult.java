package blackjack.domain.game;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.BettingTable;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.ResultStateDto;

import java.util.Map;

public final class GameResult {

    private final Map<Participant, Betting> resultBetting;

    private GameResult(final Map<Participant, Betting> resultBetting) {
        this.resultBetting = resultBetting;
    }

    public static GameResult of(final BettingTable table, final Participants participants) {
        Map<Participant, Betting> bettingTable = table.getTable();
        calculateResult(bettingTable, participants);
        return new GameResult(bettingTable);
    }

    private static void calculateResult(final Map<Participant, Betting> table, final Participants participants) {
        final Participant dealer = participants.getDealer();

        for (final Participant player : participants.getPlayers()) {
            final ResultState playerState = ResultState.getState(
                    ResultStateDto.from(player),
                    ResultStateDto.from(dealer));

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
