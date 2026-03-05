package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {


    @Test
    @DisplayName("인자로 넘겨받은 카드를 핸즈에 추가한다.")
    void addACard() {
        // given
        Hands hands = Hands.empty();
        Card card = new Card(Rank.ACE, Suit.CLOVER);

        // when && then
        assertThatCode(() -> hands.addACard(card))
                .doesNotThrowAnyException();

    }
}