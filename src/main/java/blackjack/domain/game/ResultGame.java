package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.dto.ResultParticipantDto;

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
        final ResultParticipantDto dealerResultDto = ResultParticipantDto.from(dealer);

        for (final Participant player : participants.getPlayers()) {
            final ResultParticipantDto playerResultDto = ResultParticipantDto.from(player);
            final ResultState playerState = ResultState.getState(playerResultDto, dealerResultDto);
            final int bettingMoney = resultBetting.get(player);

            resultBetting.put(player, playerState.calculateProfit(bettingMoney));
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
