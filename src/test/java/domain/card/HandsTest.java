package domain.card;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {
    @Test
    @DisplayName("중복되지 않는 카드를 뽑는다.")
    void draw() {
        final Hands hands = Hands.makeOnePack();
        final Set<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(hands.draw());
        }
        Assertions.assertThat(cards).size().isEqualTo(52);
    }

    @Test
    @DisplayName("52장이 넘는 카드를 뽑을 경우 예외를 발생한다.")
    void handsSize() {
        final Hands hands = Hands.makeOnePack();
        for (int i = 0; i < 52; i++) {
            hands.draw();
        }
        Assertions.assertThatCode(hands::draw).isInstanceOf(IllegalStateException.class);
    }
}
