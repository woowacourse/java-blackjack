package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuestTest {

    @Test
    @DisplayName("히트 조건을 가지는 전략 패턴에 맞게 히트하는지 확인")
    void hitStrategyTest() {
        Deck deck = new Deck();
        Guest a = new Guest("a", deck, (p) -> HitFlag.Y);
        assertThat(a.checkHitFlag())
                .isEqualTo(HitFlag.Y);
    }

    @Test
    @DisplayName("게스트가 카드를 보여줄 때 모든 카드를 공개하는지 확인")
    void showCardsTest() {
        Deck deck = new Deck();
        Guest a = new Guest("a", deck, (p) -> HitFlag.Y);
        assertThat(a.getShowCards().getCardValues().size())
                .isEqualTo(2);
    }
}
