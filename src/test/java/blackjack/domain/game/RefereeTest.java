package blackjack.domain.game;

import java.util.List;
import java.util.Map;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.card.*;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private final Referee referee = new Referee();

    @Test
    void testResult() {
        Participant dealer = new Dealer();
        hit(dealer, List.of("10"));
        Players players = createPlayers();

        List<Result> result = referee.judgeResult(dealer, players);

        Assertions.assertThat(result).isEqualTo(List.of(Result.WIN, Result.LOSE));
    }

    private Players createPlayers() {
        Players players = new Players("무민, 아마란스");
        Player score11Player = players.getPlayers().get(0);
        Player scoreBurstPlayer = players.getPlayers().get(1);

        hit(score11Player, List.of("9", "2"));
        hit(scoreBurstPlayer, List.of("10", "5", "8"));
        return players;
    }

    private void hit(Participant participant, List<String> symbols) {
        for (String symbol : symbols) {
            participant.hit(new StandardCard(Pattern.CLUB, symbol));
        }
    }

    @Test
    void testDealerBurstResult() {
        Players players = createPlayers();
        Participant dealer = new Dealer();
        hit(dealer, List.of("10", "4", "9"));

        List<Result> result = referee.judgeResult(dealer, players);

        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.LOSE));
    }

    @Test
    void countDealerResult() {
        List<Result> results = List.of(Result.WIN, Result.DRAW, Result.WIN, Result.LOSE);

        Map<String, Long> dealerResult = referee.countDealerResult(results);

        Assertions.assertThat(dealerResult.get(Result.WIN.getResult())).isEqualTo(2);
        Assertions.assertThat(dealerResult.get(Result.DRAW.getResult())).isEqualTo(1);
        Assertions.assertThat(dealerResult.get(Result.LOSE.getResult())).isEqualTo(1);
    }


}