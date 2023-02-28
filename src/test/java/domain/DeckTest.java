package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Nested
    class 생성 {
        @Test
        void test_생성_should_중복되는인스턴스가없어야한다_when_생성되었을때() {
            //given
            Deck deck = Deck.getInstance();

            //when

            //then
            assertThat(deck).extracting("cards", InstanceOfAssertFactories.collection(Set.class))
                    .size()
                    .isEqualTo(52);
        }
    }
}