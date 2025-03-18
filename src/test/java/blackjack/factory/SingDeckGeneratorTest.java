package blackjack.factory;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingDeckGeneratorTest {

    @DisplayName("덱을 만들 때 카드의 개수는 52개이다")
    @Test
    void test1() {

        // given & when
        Deck deck = SingleDeckFactory.generate();

        // then
        assertThat(deck.getCards()).hasSize(52);
    }

    @DisplayName("카드 덱은 중복되면 안된다.")
    @Test
    void test2() {
        // given
        Deck deck = SingleDeckFactory.generate();

        // when
        int uniqueCardSize = new HashSet<>(deck.getCards()).size();

        // then
        assertThat(uniqueCardSize).isEqualTo(52);
    }
}
