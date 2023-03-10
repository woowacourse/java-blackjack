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

    @Test
    void twoBlackJack() {
        Dealer blackjackDealer = new Dealer();
        Players blackjackPlayers = new Players("무민,아마란스");
        hitBlackJack(blackjackDealer);
        hitBlackJack(blackjackPlayers.getPlayers().get(0));
        hitBlackJack(blackjackPlayers.getPlayers().get(1));

        List<Result> results = referee.judgeResult(blackjackDealer, blackjackPlayers);

        Assertions.assertThat(results).isEqualTo(List.of(Result.DRAW, Result.DRAW));
    }

    private void hitBlackJack(Participant participant) {
        participant.hit(new StandardCard(Pattern.SPADE, "10"));
        participant.hit(new AceCard(Pattern.SPADE));
    }

    @Test
    void onlyDealerBlackJack() {
        Dealer blackjackDealer = new Dealer();
        Players players = new Players("무민,아마란스");
        hitBlackJack(blackjackDealer);
        Player score14Player = players.getPlayers().get(0);
        Player score21Player = players.getPlayers().get(1);
        hit(score14Player, List.of("6", "8"));
        hit(score21Player, List.of("10", "5", "6"));

        List<Result> results = referee.judgeResult(blackjackDealer, players);

        Assertions.assertThat(results).isEqualTo(List.of(Result.LOSE, Result.LOSE));
    }

    @Test
    void onlyPlayerBlackJack() {
        Dealer dealer = new Dealer();
        hit(dealer, List.of("7", "6", "8"));
        Players blackjackplayers = new Players("무민,아마란스");
        hitBlackJack(blackjackplayers.getPlayers().get(0));
        hitBlackJack(blackjackplayers.getPlayers().get(1));

        List<Result> results = referee.judgeResult(dealer, blackjackplayers);

        Assertions.assertThat(results).isEqualTo(List.of(Result.BLACKJACK, Result.BLACKJACK));
    }
}