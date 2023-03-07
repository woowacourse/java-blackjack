package blackjack.domain;

import static blackjack.domain.Denomination.QUEEN;
import static blackjack.domain.Denomination.SEVEN;
import static blackjack.domain.Denomination.SIX;
import static blackjack.domain.Suit.CLUB;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUpDealer() {
        dealer = new Dealer("딜러");
    }

    @DisplayName("카드를 가지고 있지 않을 때 첫번째 카드를 요청하면 예외를 발생한다.")
    @Test
    void should_ThrowException_When_OpenFirstCardFromEmptyCards() {
        assertThatThrownBy(() -> dealer.openFirstCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("딜러가 아직 카드를 가지고 있지 않습니다.");
    }

    @DisplayName("딜러의 카드 합이 17 미만이면 참을 반환한다.")
    @Test
    void should_ReturnTrue_WhenSumOfCardsUnder17() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SIX));

        assertThat(dealer.isAvailable()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 17 이상이면 거짓을 반환한다.")
    @Test
    void should_ReturnFalse_WhenSumOfCardsOver17() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SEVEN));

        assertThat(dealer.isAvailable()).isFalse();
    }

    @DisplayName("합을 비교해 최종 승패를 결정한다.")
    @Test
    void should_ReturnJudgeResult_When_Given_Player() {
        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SEVEN));

        Player player = new Player("pobi");
        player.take(new Card(SPADE, QUEEN));
        player.take(new Card(CLUB, SIX));
        assertThat(dealer.judge(player)).isEqualTo(JudgeResult.LOSE);
    }
}
