package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @ParameterizedTest
    @DisplayName("카드 출력 이름은 '숫자 + 무늬' 형식이다")
    @CsvSource({
            "HEART,   ACE,   A하트",
            "SPADE,   KING,  K스페이드",
            "DIAMOND, THREE, 3다이아몬드",
            "CLUB,    SEVEN, 7클로버"
    })
    void displayName(Suit suit, Rank rank, String expected) {
        Card card = new Card(suit, rank);
        assertThat(card.getDisplayName()).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드의 점수는 Rank의 점수를 반환한다")
    void cardValue() {
        Card card = new Card(Suit.HEART, Rank.QUEEN);
        assertThat(card.getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("ACE 카드인지 여부를 확인할 수 있다")
    void isAce() {
        Card aceCard = new Card(Suit.CLUB, Rank.ACE);
        Card kingCard = new Card(Suit.CLUB, Rank.KING);

        assertThat(aceCard.isAce()).isTrue();
        assertThat(kingCard.isAce()).isFalse();
    }
}
