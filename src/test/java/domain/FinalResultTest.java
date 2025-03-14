package domain;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalResultTest {

    private Player player1 = new Player(new Nickname("체체"));
    private Player player2 = new Player(new Nickname("새로이"));
    private Player player3 = new Player(new Nickname("체로이"));
    private Dealer dealer = new Dealer(new Nickname("딜러"));
    private Card card1 = new Card(Rank.KING, Shape.CLOVER);
    private Card card2 = new Card(Rank.JACK, Shape.CLOVER);
    private Card card3 = new Card(Rank.ACE, Shape.CLOVER);

    @BeforeEach
    void setUp() {
        player1.hit(card1);
        player1.hit(card2);
        player1.hit(card3);
        player2.hit(card2);
        player3.hit(card1);
        player3.hit(card2);
        dealer.hit(card1);
        dealer.hit(card2);
    }

    @DisplayName("플레이어들의 승패 결과를 생성한다.")
    @Test
    void 플레이어들의_승패_결과를_생성한다() {

        // given

        // when
        final Map<Player, FinalResult> finalResults = FinalResult.makePlayerResult(List.of(player1, player2, player3),
                dealer);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(finalResults.get(player1)).isEqualTo(FinalResult.WIN);
            softly.assertThat(finalResults.get(player2)).isEqualTo(FinalResult.LOSE);
            softly.assertThat(finalResults.get(player3)).isEqualTo(FinalResult.DRAW);
        });
    }

    @DisplayName("딜러의 승패 결과를 생성한다.")
    @Test
    void 딜러의_승패_결과를_생성한다() {

        // given
        final Player player1 = new Player(new Nickname("체체"));
        final Player player2 = new Player(new Nickname("새로이"));
        final Player player3 = new Player(new Nickname("체로이"));
        final Player player4 = new Player(new Nickname("짱구"));
        final Dealer dealer1 = new Dealer(new Nickname("딜러"));
        final Dealer dealer2 = new Dealer(new Nickname("훈이"));

        final Card card1 = new Card(Rank.KING, Shape.CLOVER);
        final Card card2 = new Card(Rank.JACK, Shape.CLOVER);
        final Card card3 = new Card(Rank.ACE, Shape.CLOVER);
        final Card card4 = new Card(Rank.TEN, Shape.CLOVER);

        player1.hit(card1);
        player1.hit(card2);
        player1.hit(card3);
        player2.hit(card2);
        player3.hit(card1);
        player3.hit(card2);
        player4.hit(card1);
        player4.hit(card2);
        player4.hit(card3);
        player4.hit(card4);
        dealer1.hit(card1);
        dealer1.hit(card2);
        dealer2.hit(card1);
        dealer2.hit(card2);
        dealer2.hit(card3);
        dealer2.hit(card4);

        final Map<Player, FinalResult> finalResults1 = FinalResult.makePlayerResult(
                List.of(player1, player2, player3, player4), dealer1);
        final Map<Player, FinalResult> finalResults2 = FinalResult.makePlayerResult(List.of(player1), dealer2);

        // when
        final Map<FinalResult, Integer> dealerResult1 = FinalResult.makeDealerResult(finalResults1);
        final Map<FinalResult, Integer> dealerResult2 = FinalResult.makeDealerResult(finalResults2);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealerResult1.get(FinalResult.WIN)).isEqualTo(1);
            softly.assertThat(dealerResult1.get(FinalResult.LOSE)).isEqualTo(2);
            softly.assertThat(dealerResult1.get(FinalResult.DRAW)).isEqualTo(1);
            softly.assertThat(dealerResult2.get(FinalResult.WIN)).isEqualTo(1);
        });
    }
}
