package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerCardsTest {
    @DisplayName("추가 및 사이즈 테스트")
    @Test
    void create() {
        PlayerCards cards = new PlayerCards();
        assertThat(cards.size()).isEqualTo(0);
        cards.add(Card.valueOf(CardShape.CLUB, CardNumber.ACE));
        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("카드 포함 테스트")
    @Test
    void crate() {
        PlayerCards cards = new PlayerCards();
        assertThat(cards.isContains(CardNumber.ACE)).isFalse();
        cards.add(Card.valueOf(CardShape.CLUB, CardNumber.ACE));
        assertThat(cards.isContains(CardNumber.ACE)).isTrue();
    }
}
