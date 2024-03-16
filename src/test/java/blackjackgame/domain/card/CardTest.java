package blackjackgame.domain.card;

import static blackjackgame.domain.card.CardName.*;
import static blackjackgame.domain.card.CardType.*;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardTest {

    private static Stream<Arguments> isCardNameAceParameters() {
        return Stream.of(
                Arguments.of(new Card(ACE, SPADE), true),
                Arguments.of(new Card(TWO, SPADE), false));
    }

    @Test
    @DisplayName("카드 이름과 카드 타입으로 카드 클래스 생성자를 만들 수 있다.")
    void createCardConstructorWithCardNameAndCardTypeTest() {
        Assertions.assertThatCode(() -> new Card(ACE, SPADE))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("isCardNameAceParameters")
    @DisplayName("카드 이름이 에이스인지 확인한다.")
    void isCardNameAceTest(Card card, boolean expected) {
        boolean actual = card.isCardNameAce();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
