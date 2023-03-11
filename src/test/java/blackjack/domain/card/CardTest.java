package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Ace인 경우 isAce() 테스트")
    void isAceTest_ace() {
        // given
        Card card = new Card(Number.ACE, Pattern.HEART);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Ace가 아닌 경우 isAce() 테스트")
    void isAceTest_notAce() {
        // given
        Card card = new Card(Number.K, Pattern.HEART);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("카드를 블랙잭 점수로 변환 테스트")
    void convertToBlackjackScore() {
        // given
        Card card = new Card(Number.Q, Pattern.HEART);

        // expect
        assertThat(card.convertToBlackjackScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 정보 받아오기")
    void getCardInfo() {
        // given
        Card card = new Card(Number.ACE, Pattern.HEART);

        // expect
        assertThat(card.getCardNumber().getState() + card.getCardPattern().getName()).isEqualTo("A하트");
    }
}
