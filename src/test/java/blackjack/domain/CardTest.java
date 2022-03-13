package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 생성")
    void createCard() {
        assertThatCode(() -> new Card(Suit.DIAMOND, Denomination.ACE))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드의 포인트 얻기")
    void getCardPoint() {
        Card card = new Card(Suit.CLOVER, Denomination.JACK);

        assertThat(card.getPoint()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 에이스 판별")
    void isAce() {
        Card card = new Card(Suit.DIAMOND, Denomination.ACE);

        assertThat(card.isAce()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 에이스 아님 판별")
    void isNotAce() {
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);

        assertThat(card.isAce()).isEqualTo(false);
    }


}
