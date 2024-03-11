package domain;

import domain.participant.*;
import domain.result.GameResult;
import domain.result.GameResultStatus;
import view.dto.result.GameResultDto;

import java.util.stream.IntStream;

public class BlackjackGame {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int DEALER_HIT_THRESHOLD = 16;

    public void ready(final Dealer dealer, final Players players) {
        giveCards(dealer, dealer);
        players.forEach(player -> giveCards(dealer, player));
    }

    private void giveCards(final Dealer dealer, final Participant participant) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                 .forEach(__ -> dealer.deal(participant));
    }

    public GameResultDto resultsOf(final Dealer dealer, final Players players) {
        GameResult gameResult = new GameResult();
        players.forEach(player -> {
            GameResultStatus result = dealer.resultStatusOf(player);
            gameResult.put(player, result);
        });
        return GameResultDto.from(gameResult, gameResult.ofDealer());
    }
}
