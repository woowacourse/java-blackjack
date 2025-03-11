package blackjack.factory;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingDeckGeneratorTest {

    @DisplayName("덱을 만들 때 카드의 개수는 52개이다")
    @Test
    void test1() {
        // given
        SingDeckGenerator singDeckGenerator = new SingDeckGenerator();

        // when
        List<Card> cards = singDeckGenerator.generate();

        // then
        assertThat(cards).hasSize(52);
    }

    @DisplayName("카드 덱은 중복되면 안된다.")
    @Test
    void test2() {
        // given
        SingDeckGenerator singDeckGenerator = new SingDeckGenerator();

        List<Card> cards = singDeckGenerator.generate();

        int uniqueCardSize = new HashSet<>(cards).size();

        assertThat(uniqueCardSize).isEqualTo(52);
    }
}
