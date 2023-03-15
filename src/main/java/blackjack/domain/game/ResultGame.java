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

    public static ResultGame from(final Map<Participant, Betting> resultBetting) {
        return new ResultGame(resultBetting);
    }

    public void betMoney(final Participant participant, final Betting betting) {
        resultBetting.put(participant, betting);
    }

    public void calculateResult(final Participants participants) {
        final Participant dealer = participants.getDealer();

        for (final Participant player : participants.getPlayers()) {
            final ResultState playerState = ResultState.getState(
                    ResultDto.from(player),
                    ResultDto.from(dealer));

            final Betting money = resultBetting.get(player);
            resultBetting.put(player, playerState.calculateProfit(money.getValue()));
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
