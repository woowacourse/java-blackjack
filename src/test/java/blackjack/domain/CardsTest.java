package blackjack.domain;

import static blackjack.domain.Letter.ACE;
import static blackjack.domain.Letter.JACK;
import static blackjack.domain.Letter.KING;
import static blackjack.domain.Letter.QUEEN;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.HEART;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    @DisplayName("카드 문자의 총 합을 계산해 반환한다.")
    @Test
    void should_ReturnSumOfCards() {
        Cards cards = new Cards(List.of(new Card(SPADE, JACK), new Card(CLUB, QUEEN)));

        assertThat(cards.sumScore()).isEqualTo(JACK.getValue() + QUEEN.getValue());
    }

    @DisplayName("문자 중 ACE는 기본값을 1로 하되, 추가 값 10을 더할 수 있다.")
    @Test
    void should_Add10ForACE_When_SumIsUnderBustLimit() {
        Cards cards = new Cards(List.of(new Card(SPADE, ACE), new Card(CLUB, QUEEN)));

        assertThat(cards.sumScore()).isEqualTo(ACE.getValue() + QUEEN.getValue() + 10);
    }

    @DisplayName("ACE가 여러 장이라면 버스트 되지 않을 때까지만 추가 값을 더한다.")
    @Test
    void should_Add10ForACEs_When_SumIsUnderBustLimit() {
        Cards cards = new Cards(
                List.of(new Card(SPADE, ACE), new Card(HEART, ACE), new Card(SPADE, KING), new Card(CLUB, QUEEN)));

        assertThat(cards.sumScore()).isEqualTo(22);
    }
}
