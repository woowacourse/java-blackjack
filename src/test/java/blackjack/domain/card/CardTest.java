package blackjack.domain.card;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.DenominationArgumentsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class CardTest {

    @DisplayName("하나의 Denomination 과 하나의 Suit 를 활용하여 생성한다.")
    @Test
    void 카드_생성_정상() {
        Assertions.assertDoesNotThrow(() -> new Card(ACE, SPADE));
    }

    @DisplayName("현재 점수를 기반으로 현재 끗수의 숫자를 더해 반환한다.")
    @Test
    void 점수_더하기() {
        Card card = new Card(THREE, HEART);

        int result = card.calculateScore(10);

        assertThat(result).isEqualTo(13);
    }

    @DisplayName("현재 점수를 기반으로 계산한다. ACE 는 bust 가 최대한 일어나지 않도록 연산된다.")
    @ParameterizedTest
    @ArgumentsSource(DenominationArgumentsProvider.class)
    void 점수_계산(int beforeScore, Denomination denomination, int afterScore) {
        Card card = new Card(denomination, HEART);
        int result = card.calculateScore(beforeScore);

        assertThat(result).isEqualTo(afterScore);
    }
}
