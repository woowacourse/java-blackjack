package blackjack.domain;

import java.util.List;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RefereeTest {

    Referee referee;

    @BeforeEach
    void init() {
        referee = new Referee();
    }

    @Test
    void testResult() {

        //int dealerScore = 20;
        //List<Integer> playerScores = List.of(21, 9, 20, -1);

        Participant dealer = new Dealer();
        Players players = new Players("무민,아마란스,프리지아,수국");
        Card card1 = new StandardCard(Pattern.CLUB, "10");
        Card card2 = new StandardCard(Pattern.CLUB, "4");
        Card card3 = new StandardCard(Pattern.CLUB, "6");

        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        Card card4 = new StandardCard(Pattern.SPADE, "10");
        Card card5 = new AceCard(Pattern.SPADE);
        players.getPlayers().get(0).hit(card4);
        players.getPlayers().get(0).hit(card5);

        Card card7 = new StandardCard(Pattern.CLUB, "9");
        players.getPlayers().get(1).hit(card7);

        Card card8 = new StandardCard(Pattern.HEART, "10");
        Card card9 = new StandardCard(Pattern.HEART, "4");
        Card card10 = new StandardCard(Pattern.HEART, "6");

        players.getPlayers().get(2).hit(card8);
        players.getPlayers().get(2).hit(card9);
        players.getPlayers().get(2).hit(card10);

        Card card11 = new StandardCard(Pattern.HEART, "10");
        Card card12 = new StandardCard(Pattern.DIAMOND, "10");
        Card card13 = new CourtCard(Pattern.DIAMOND, "K");

        players.getPlayers().get(3).hit(card11);
        players.getPlayers().get(3).hit(card12);
        players.getPlayers().get(3).hit(card13);

        List<Result> result = referee.judgeResult(dealer, players);
        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.LOSE, Result.DRAW, Result.LOSE));
    }

    @Test
    void testTwoBurstResult() {

        //int dealerScore = -1;
        //List<Integer> playerScores = List.of(21, 9, 20, -1);

        Participant dealer = new Dealer();
        Players players = new Players("무민,아마란스,프리지아,수국");
        Card card1 = new StandardCard(Pattern.CLUB, "10");
        Card card2 = new StandardCard(Pattern.CLUB, "4");
        Card card3 = new StandardCard(Pattern.CLUB, "9");

        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        Card card4 = new StandardCard(Pattern.SPADE, "10");
        Card card5 = new AceCard(Pattern.SPADE);
        players.getPlayers().get(0).hit(card4);
        players.getPlayers().get(0).hit(card5);

        Card card7 = new StandardCard(Pattern.CLUB, "9");
        players.getPlayers().get(1).hit(card7);

        Card card8 = new StandardCard(Pattern.HEART, "10");
        Card card9 = new StandardCard(Pattern.HEART, "4");
        Card card10 = new StandardCard(Pattern.HEART, "6");

        players.getPlayers().get(2).hit(card8);
        players.getPlayers().get(2).hit(card9);
        players.getPlayers().get(2).hit(card10);

        Card card11 = new StandardCard(Pattern.HEART, "10");
        Card card12 = new StandardCard(Pattern.DIAMOND, "10");
        Card card13 = new CourtCard(Pattern.DIAMOND, "K");

        players.getPlayers().get(3).hit(card11);
        players.getPlayers().get(3).hit(card12);
        players.getPlayers().get(3).hit(card13);

        List<Result> result = referee.judgeResult(dealer, players);
        Assertions.assertThat(result)
            .isEqualTo(List.of(Result.WIN, Result.WIN, Result.WIN, Result.LOSE));
    }


}