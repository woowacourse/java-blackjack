package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    @Test
    @DisplayName("현재 핸즈가 가지고 있는 카드들의 총점을 계산한다.")
    void calculateTotalScore() {
        // given
        Hands hands = Hands.empty();

        hands.addACard(new Card(Rank.J, Suit.CLOVER));
        hands.addACard(new Card(Rank.K, Suit.DIAMOND));

        // when & then
        assertThat(hands.calculateTotalScore()).isEqualTo(20);
    }
}