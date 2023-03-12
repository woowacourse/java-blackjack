package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private final String name = "test";
    private final Card spadeAce = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card cloverEight = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(spadeAce, cloverEight);
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User player = new Player(name, initialGroup);

        assertThat(player.getInitialHoldingCards()).containsExactly(spadeAce, cloverEight);
    }

    @Test
    @DisplayName("플레이어 이름으로 딜러 코드 입력시 오류 발생 테스트")
    void dealerNameCodeValidationTest() {
        assertThatThrownBy(() -> new Player(Dealer.DEALER_NAME_CODE, initialGroup))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Player.NAME_EQUAL_DEALER_CODE_EXCEPTION_MESSAGE);
    }
}
