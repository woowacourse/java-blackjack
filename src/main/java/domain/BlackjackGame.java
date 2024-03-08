package domain;

import java.util.EnumMap;
import java.util.stream.IntStream;

import domain.participant.Dealer;
import domain.participant.DealerGameResult;
import domain.participant.Participant;
import domain.participant.Players;
import domain.participant.PlayersGameResult;
import view.dto.GameResultDto;
import view.dto.participant.DealerResultDto;
import view.dto.participant.PlayerResultsDto;

public class BlackjackGame {

    public static final int INITIAL_CARD_COUNT = 2;
    public static final int DEALER_THRESHOLD = 16;

    public void ready(final Dealer dealer, final Players players) {
        giveCards(dealer, dealer);
        players.forEach(player -> giveCards(dealer, player));
    }

    private void giveCards(final Dealer dealer, final Participant participant) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .forEach(__ -> dealer.deal(participant));
    }

    public GameResultDto resultsOf(final Dealer dealer, final Players players) {
        DealerGameResult dealerResults = new DealerGameResult(new EnumMap<>(GameResultStatus.class));
        PlayersGameResult playerResults = new PlayersGameResult();
        players.forEach(player -> {
            GameResultStatus playerResultStatus = dealer.resultStatusOf(player);
            GameResultStatus dealerResultStatus = playerResultStatus.opposite();
            playerResults.put(player, playerResultStatus);
            dealerResults.put(dealerResultStatus);
        });
        return new GameResultDto(new DealerResultDto(dealer, dealerResults),
                new PlayerResultsDto(playerResults));
    }

}
