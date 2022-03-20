package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        cards.add(Card.of(Symbol.CLOVER, Denomination.EIGHT));
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외가 발생한다.")
    void addDuplicatedCard() {
        assertThatThrownBy(() -> cards.add(Card.of(Symbol.CLOVER, Denomination.EIGHT)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 받을 수 없습니다.");
    }

    @Test
    @DisplayName("Ace가 없는 경우의 합계를 계산한다.")
    void calculateScore() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(cards.calculateScore()).isEqualTo(new Score(18));
    }

    @Test
    @DisplayName("Ace가 1로 계산되는 경우의 점수 합계를 계산한다.")
    void calculateScoreWithAceOne() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));
        cards.add(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(cards.calculateScore()).isEqualTo(new Score(19));
    }

    @Test
    @DisplayName("Ace가 11로 계산되는 경우의 점수 합계를 계산한다.")
    void calculateScoreWithAceEleven() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));

        assertThat(cards.calculateScore()).isEqualTo(new Score(19));
    }

    @Test
    @DisplayName("Ace가 여러개인 경우의 점수 합계를 계산한다.")
    void calculateScoreWithAceCountThree() {
        cards.add(Card.of(Symbol.CLOVER, Denomination.ACE));
        cards.add(Card.of(Symbol.HEART, Denomination.ACE));
        cards.add(Card.of(Symbol.DIAMOND, Denomination.ACE));

        assertThat(cards.calculateScore()).isEqualTo(new Score(21));
    }
}