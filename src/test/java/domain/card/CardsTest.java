package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @ParameterizedTest
    @CsvSource({"1,12", "10,21"})
    @DisplayName("처음 뽑은 두 개 카드의 합을 구한다.")
    void sumInitCards(int number, int sum) {
        Cards cards = new Cards(List.of(
                new Card(number, Shape.CLUB),
                new Card(1, Shape.SPADE)
        ));
        cards.receive(new Card(1, Shape.HEART));

        assertThat(cards.sumInitCards()).isEqualTo(sum);
    }

    @ParameterizedTest
    @CsvSource({"1,12", "10,21", "13,21"})
    @DisplayName("Ace가 있을 때의 최선 합을 구한다.")
    void sumContainsAce(int number, int sum) {
        Cards cards = new Cards(List.of(
                new Card(1, Shape.CLUB),
                new Card(number, Shape.CLUB)
        ));

        assertThat(cards.bestSum()).isEqualTo(sum);
    }

    @Test
    @DisplayName("Ace가 여러 개인 경우 최선 합을 구한다.")
    void sumOnlyContainsAce() {
        Cards cards = new Cards(List.of(
                new Card(1, Shape.CLUB),
                new Card(1, Shape.SPADE)
        ));
        cards.receive(new Card(1, Shape.HEART));
        cards.receive(new Card(1, Shape.DIAMONDS));

        assertThat(cards.bestSum()).isEqualTo(14);
    }
}
