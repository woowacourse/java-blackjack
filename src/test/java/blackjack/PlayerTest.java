package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("플레이어는 이름과 카드를 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        // when & then
        assertThatCode(() -> new Player("히로", hand))
                .doesNotThrowAnyException();
    }


}
