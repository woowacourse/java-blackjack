package blackjack.domain.player;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.HitFlag;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Guest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuestTest {

    @Test
    @DisplayName("히트 조건을 가지는 전략 패턴에 맞게 히트하는지 확인")
    void hitStrategyTest() {
        Guest a = new Guest("a", (p) -> HitFlag.Y);
        assertThat(a.checkHitFlag())
                .isEqualTo(HitFlag.Y);
    }

    @Test
    @DisplayName("게스트가 카드를 보여줄 때 모든 카드를 공개하는지 확인")
    void showCardsTest() {
        Deck deck = new Deck();
        Guest a = new Guest("a", (p) -> HitFlag.Y);
        a.hit(deck.draw());
        a.hit(deck.draw());
        assertThat(a.getShowCards().getCards().size())
                .isEqualTo(2);
    }
}
