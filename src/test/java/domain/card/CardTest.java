package domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.Rank.*;
import static blackjack.domain.card.Symbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CardTest {

    @ParameterizedTest
    @MethodSource("rankScoreStream")
    @DisplayName("성공: 각 카드가 정확한 점수를 갖음")
    void score_NoException_WithMethodSource(Rank rank, int score) {
        Card card = new Card(rank, DIAMOND);
        assertThat(card.getScore()).isEqualTo(score);
    }

    static Stream<Arguments> rankScoreStream() {
        return Stream.of(
                arguments(KING, 10), arguments(QUEEN, 10), arguments(JACK, 10), arguments(TEN, 10),
                arguments(NINE, 9), arguments(EIGHT, 8), arguments(SEVEN, 7),
                arguments(SIX, 6), arguments(FIVE, 5), arguments(FOUR, 4),
                arguments(THREE, 3), arguments(TWO, 2), arguments(ACE, 1)
        );
    }

    @Test
    @DisplayName("True: Ace 카드는 자신이 ACE 카드임을 확인")
    void isAce_True_Ace() {
        Card aceCard = new Card(ACE, DIAMOND);
        assertThat(aceCard.isAce()).isTrue();
    }

    @Test
    @DisplayName("False: Ace 카드 외에 자신이 ACE 카드임을 확인")
    void isAce_False_NotAce() {
        Card kingCard = new Card(KING, DIAMOND);
        assertThat(kingCard.isAce()).isFalse();
    }
}
