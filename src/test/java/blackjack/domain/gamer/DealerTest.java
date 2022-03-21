package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerTest {
    @Test
    @DisplayName("보유 카드 번호 합이 16을 초과하는지 확인한다.")
    void checkCardsNumberSum() {
        Card card1 = new Card(CardShape.SPADE, CardNumber.TEN);
        Card card2 = new Card(CardShape.SPADE, CardNumber.FIVE);
        Dealer dealer = new Dealer(List.of(card1, card2));
        assertThat(dealer.canDraw()).isEqualTo(true);
    }

    @Test
    @DisplayName("Dealer를 생성할 때, 카드를 2장 이상 입력할 시 에러갑 발생한다.")
    void validateInitCardSize() {
        Card card1 = new Card(CardShape.SPADE, CardNumber.TEN);
        Card card2 = new Card(CardShape.SPADE, CardNumber.FIVE);
        Card card3 = new Card(CardShape.SPADE, CardNumber.SIX);
        assertThatThrownBy(() -> {
            new Dealer(List.of(card1, card2, card3));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초기 카드는 2장만 Draw 해야 합니다.");
    }
}