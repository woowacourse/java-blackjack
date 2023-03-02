package blackjack.domain;

import static blackjack.domain.Number.QUEEN;
import static blackjack.domain.Number.SEVEN;
import static blackjack.domain.Number.SIX;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @DisplayName("딜러의 카드 합이 17 미만이면 참을 반환한다.")
    @Test
    void should_ReturnTrue_WhenSumOfCardsUnder17() {
        Dealer dealer = new Dealer();

        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SIX));

        assertThat(dealer.isUnderTakeLimit()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 17 이상이면 거짓을 반환한다.")
    @Test
    void should_ReturnFalse_WhenSumOfCardsOver17() {
        Dealer dealer = new Dealer();

        dealer.take(new Card(SPADE, QUEEN));
        dealer.take(new Card(CLUB, SEVEN));

        assertThat(dealer.isUnderTakeLimit()).isFalse();
    }
}
