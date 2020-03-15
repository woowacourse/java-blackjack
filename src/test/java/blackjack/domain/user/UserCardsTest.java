package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserCardsTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("카드가 한 장도 없는 상태에서 초기화됐을 경우")
    void emptyHandTest(List<Card> cards) {
        assertThatThrownBy(() -> new UserCards(cards))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("카드가 없습니다");
    }

    @Test
    @DisplayName("총 점수가 잘 계산됐는지 확인")
    void totalScoreTest() {
        Card card1 = new Card(Suit.CLUB, Symbol.TEN);
        Card card2 = new Card(Suit.HEART, Symbol.KING);
        UserCards userCards = new UserCards(Arrays.asList(card1, card2));
        assertThat(userCards.calculateTotalScore()).isEqualTo(20);
    }
}
