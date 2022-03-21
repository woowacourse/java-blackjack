package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Player;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("이름으로 인한 플레이어 생성 오류 테스트")
    void playerInvalidTest(String playerName, String testName) {
        BettingMoney bettingMoney = BettingMoney.of(10);
        assertThatThrownBy(() -> Player.of(playerName, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of("", "빈문자 입력"),
                Arguments.of("pobiash", "6글자 초과 입력")
        );
    }

    @Test
    @DisplayName("베팅 금액으로 인한 플레이어 생성 오류 테스트")
    void invalidBettingMoneyTest() {
        assertThatThrownBy(() -> BettingMoney.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 10원 단위로 입력해야 합니다.");
    }

    @Test
    @DisplayName("플레이어에게 카드가 추가되는지 테스트한다.")
    void addCard() {
        Player pepper = Player.of("페퍼", BettingMoney.of(10));
        int pepperCardsSize = pepper.getCards().size();
        pepper.addCard(Card.of(CardShape.HEART, CardNumber.KING));

        assertThat(pepper.getCards().size()).isEqualTo(pepperCardsSize + 1);
    }
}
