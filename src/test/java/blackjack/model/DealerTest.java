package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("점수가 17미만일 때 Hit 가능")
    void test_can_hit_before_17() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Suit.HEART, Rank.JACK));
        dealer.addCard(new Card(Suit.CLOVER, Rank.SIX));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("점수가 17이상일 때 Hit 불가")
    void test_cannot_hit_at_17() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Suit.HEART, Rank.JACK));
        dealer.addCard(new Card(Suit.CLOVER, Rank.SEVEN));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("GameResult 기록 결과 정상")
    void test_gameresult_add_and_count() {
        Dealer dealer = new Dealer();

        dealer.addResult(GameResult.WIN);
        dealer.addResult(GameResult.WIN);
        dealer.addResult(GameResult.DRAW);
        dealer.addResult(GameResult.LOSE);

        EnumMap<GameResult, Integer> gameresults = dealer.getGameResults();
        assertThat(gameresults.get(GameResult.WIN)).isEqualTo(2);
        assertThat(gameresults.get(GameResult.DRAW)).isEqualTo(1);
        assertThat(gameresults.get(GameResult.LOSE)).isEqualTo(1);
    }
}
