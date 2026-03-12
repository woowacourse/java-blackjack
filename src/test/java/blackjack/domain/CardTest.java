package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @Test
    @DisplayName("카드가 정상적으로 생성되고, 화면 출력용 이름을 반환한다.")
    void createCardAndGetDisplayName() {
        // given
        Card card = new Card(Rank.ACE, Suit.SPADE);

        // when
        String displayName = card.getDisplayName();

        // then
        assertThat(displayName).isEqualTo("A스페이드");
    }

    @CsvSource(value = {"TWO, 2", "TEN, 10", "KING, 10", "ACE, 11"})
    @DisplayName("카드의 점수를 정확하게 반환한다.")
    @ParameterizedTest
    void getScore(Rank rank, int expectedScore) {
        // given
        Card card = new Card(rank, Suit.HEART);

        // when
        int score = card.getScore();

        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("에이스(ACE) 카드인지 여부를 정확히 판별한다.")
    void isAce() {
        // given
        Card aceCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card nonAceCard = new Card(Rank.KING, Suit.DIAMOND);

        // when & then
        assertAll(
            () -> assertThat(aceCard.isAce()).isTrue(),
            () -> assertThat(nonAceCard.isAce()).isFalse()
        );
    }
}
