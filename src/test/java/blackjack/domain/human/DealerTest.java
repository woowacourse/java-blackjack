package blackjack.domain.human;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Fixtures;
import blackjack.domain.participant.human.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private final Fixtures fx = new Fixtures();

    @Test
    @DisplayName("딜러 카드 추가/포인트 획득 기능 테스트")
    public void addCardToPlayerTest() {
        Dealer dealer = new Dealer(List.of(ACE, TEN));
        assertThat(dealer.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("딜러 히트가능 확인하는 기능 검")
    public void isAbleToHitTest() {
        Dealer dealer = new Dealer(List.of(NINE, TWO));
        assertThat(dealer.isAbleToHit())
                .isTrue();
    }
}
