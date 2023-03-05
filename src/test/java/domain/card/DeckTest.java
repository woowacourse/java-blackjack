package domain.card;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class DeckTest {

    @DisplayName("덱 제일 앞에서 카드를 지급한다.")
    @TestFactory
    Stream<DynamicTest> getSizeDynamicTest() {
        Deck deck = new Deck();
        return Stream.of(
            DynamicTest.dynamicTest("처음에는 52개의 카드를 가진다.", () -> {
                Assertions.assertThat(deck.getCurrentSize()).isEqualTo(52);
            }),
            DynamicTest.dynamicTest("카드 1개를 반환하면 1이 줄어든다.", () -> {
                Assertions.assertThat(deck.draw()).isEqualTo(new Card(Denomination.ACE, Suit.SPADE));
                Assertions.assertThat(deck.getCurrentSize()).isEqualTo(51);
            })
        );
    }
}
