package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Nested
    class InvalidCases {

        @Test
        @DisplayName("블랙잭게임은 덱과 딜러와 룰을 가져야한다.")
        void validateNotNull() {
            // given
            Deck nullDeck = null;
            Dealer nullDealer = null;
            Rule nullRule = null;

            // when & then
            assertThatThrownBy(() -> new BlackJackGame(nullDeck, nullDealer, nullRule))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
        }
    }
}
