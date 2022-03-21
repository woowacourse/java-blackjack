package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.SEVEN;
import static blackjack.fixture.Fixture.SIX;
import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private final Dealer dealer = new Dealer();
    private final Player player = new Player("pobi");

    @DisplayName("딜러의 카드 총 합이 16 이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void is_received_true() {
        dealer.deal(List.of(SIX, TEN));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 16 초과일 경우 False 를 반환하는지 확인한다.")
    @Test
    void is_received_false() {
        dealer.deal(List.of(SEVEN, TEN));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void is_lower_score_true() {
        player.deal(List.of(SIX, SIX));
        dealer.deal(List.of(SEVEN, SEVEN));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void is_lower_score_same() {
        player.deal(List.of(SIX, SEVEN));
        dealer.deal(List.of(SIX, SEVEN));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void is_lower_score_false() {
        player.deal(List.of(SEVEN, SEVEN));
        dealer.deal(List.of(SIX, SIX));

        assertThat(dealer.isLowerScore(player)).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void is_higher_score_true() {
        player.deal(List.of(SIX, SIX));
        dealer.deal(List.of(SEVEN, SEVEN));

        assertThat(dealer.isHigherScore(player)).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void is_higher_score_same() {
        player.deal(List.of(SIX, SEVEN));
        dealer.deal(List.of(SIX, SEVEN));

        assertThat(dealer.isHigherScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void is_higher_score_false() {
        player.deal(List.of(SEVEN, SEVEN));
        dealer.deal(List.of(SIX, SIX));

        assertThat(dealer.isHigherScore(player)).isFalse();
    }
}
