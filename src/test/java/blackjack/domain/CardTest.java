package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("하나의 Denomination 과 하나의 Suit 를 활용하여 생성한다.")
    @Test
    void 카드_생성_정상() {
        Denomination denomination = Denomination.ACE;
        Suit suit = Suit.SPADE;

        Assertions.assertDoesNotThrow(() -> new Card(denomination, suit));
    }

    @DisplayName("ACE 카드 점수를 조회한다.")
    @Test
    void ACE_카드_점수_조회() {
        Denomination denomination = Denomination.ACE;
        Suit suit = Suit.SPADE;

        Card card = new Card(denomination, suit);

        assertThat(card.getScore()).isEqualTo(1);
    }

    @DisplayName("KING 카드 점수를 조회한다.")
    @Test
    void KING_카드_점수_조회() {
        Denomination denomination = Denomination.KING;
        Suit suit = Suit.SPADE;

        Card card = new Card(denomination, suit);

        assertThat(card.getScore()).isEqualTo(10);
    }
}
