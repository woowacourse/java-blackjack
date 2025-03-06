package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FinalResultTest {

    @DisplayName("카드 합에 따른 승패 결과 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "15, 14, WIN", "15, 17, LOSE", "15, 15, DRAW"
    })
    void 카드_합에_따른_승패_결과_반환한다(int sumOfRank, int otherSumOfRank, FinalResult expected) {

        // given
        final FinalResult finalResult = FinalResult.findBySumOfRank(sumOfRank, otherSumOfRank);

        // when & then
        assertThat(finalResult).isEqualTo(expected);
    }

    @DisplayName("플레이어들의 승패 결과를 생성한다.")
    @Test
    void 플레이어들의_승패_결과를_생성한다() {

        // given
        Player player1 = new Player(new Nickname("체체"));
        Player player2 = new Player(new Nickname("새로이"));
        Player player3 = new Player(new Nickname("체로이"));
        Dealer dealer = new Dealer(new Nickname("딜러"));
        Card card1 = new Card(Rank.KING, Shape.CLOVER);
        Card card2 = new Card(Rank.JACK, Shape.CLOVER);
        Card card3 = new Card(Rank.ACE, Shape.CLOVER);

        player1.hit(card1);
        player1.hit(card2);
        player1.hit(card3);
        player2.hit(card2);
        player3.hit(card1);
        player3.hit(card2);
        dealer.hit(card1);
        dealer.hit(card2);

        // when
        Map<Player, FinalResult> finalResults = FinalResult.makePlayerResult(List.of(player1, player2, player3),
                dealer);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(finalResults.get(player1)).isEqualTo(FinalResult.WIN);
            softly.assertThat(finalResults.get(player2)).isEqualTo(FinalResult.LOSE);
            softly.assertThat(finalResults.get(player3)).isEqualTo(FinalResult.DRAW);
        });
    }
}
