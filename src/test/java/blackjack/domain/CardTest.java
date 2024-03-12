package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.Kind.DIAMOND;
import static blackjack.domain.Kind.SPADE;
import static blackjack.domain.Value.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @DisplayName("카드의 문양과 값을 가진 카드가 생성된다")
    @Test
    void should_CreateCard_When_GiveCardKindAndValue() {
        Card testCard1 = new Card(SPADE, ACE);
        Card testCard2 = new Card(DIAMOND, ACE);

        assertAll(
                () -> assertThat(testCard1.getKind()).isEqualTo(SPADE),
                () -> assertThat(testCard1.getValue()).isEqualTo(ACE),

                () -> assertThat(testCard2.getKind()).isEqualTo(DIAMOND),
                () -> assertThat(testCard2.getValue()).isEqualTo(ACE)
        );
    }

    @DisplayName("카드가 가진 점수를 확인할 수 있다")
    @Test
    void should_getCardScore() {
        Card testCard1 = new Card(SPADE, TEN);
        Card testCard2 = new Card(DIAMOND, FOUR);

        assertAll(
                () -> assertThat(testCard1.getScore()).isEqualTo(10),
                () -> assertThat(testCard2.getScore()).isEqualTo(4)
        );
    }
}
