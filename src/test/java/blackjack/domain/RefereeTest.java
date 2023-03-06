package blackjack.domain;

import java.util.List;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private final Referee referee = new Referee();

    @Test
    void testResult() {
        Participant dealer = new Dealer();
        dealer.hit(createStandCard(Pattern.CLUB, "10"));
        Players players = createPlayers();

        List<Result> result = referee.judgeResult(dealer, players);

        Assertions.assertThat(result).isEqualTo(List.of(Result.WIN, Result.LOSE, Result.DRAW, Result.LOSE));
    }

    private Players createPlayers() {
        Players players = new Players("무민,아마란스,프리지아,수국");
        players.getPlayers().get(0).hit(createAceCard(Pattern.SPADE));
        players.getPlayers().get(1).hit(createStandCard(Pattern.CLUB, "9"));
        players.getPlayers().get(2).hit(createStandCard(Pattern.SPADE, "10"));
        players.getPlayers().get(3).hit(createStandCard(Pattern.HEART, "10"));
        players.getPlayers().get(3).hit(createStandCard(Pattern.DIAMOND, "10"));
        players.getPlayers().get(3).hit(createCourtCard(Pattern.DIAMOND, "K"));
        return players;
    }

    private CourtCard createCourtCard(Pattern pattern, String symbol) {
        return new CourtCard(pattern, symbol);
    }

    private AceCard createAceCard(Pattern pattern) {
        return new AceCard(pattern);
    }

    private StandardCard createStandCard(Pattern pattern, String symbol) {
        return new StandardCard(pattern, symbol);
    }

    @Test
    void testDealerBurstResult() {
        Participant dealer = new Dealer();
        dealer.hit(createStandCard(Pattern.CLUB, "10"));
        dealer.hit(createStandCard(Pattern.CLUB, "4"));
        dealer.hit(createStandCard(Pattern.CLUB, "9"));
        Players players = createPlayers();

        List<Result> result = referee.judgeResult(dealer, players);

        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.WIN, Result.WIN, Result.LOSE));
    }



}