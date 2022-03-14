package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

    Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
        cards.add(Card.from(Symbol.CLOVER, Denomination.EIGHT));
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외 발생 테스트")
    void addDuplicatedCard() {
        assertThatThrownBy(() -> cards.add(Card.from(Symbol.CLOVER, Denomination.EIGHT)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드는 받을 수 없습니다.");
    }

    @Test
    @DisplayName("Ace를 고려하지 않은 플레이어의 카드 단순 합계 계산 테스트")
    void calculateScore() {
        cards.add(Card.from(Symbol.CLOVER, Denomination.ACE));

        assertThat(cards.calculateScore()).isEqualTo(9);
    }

    @ParameterizedTest(name = "카드 리스트에 Ace가 있는지 확인 테스트")
    @CsvSource(value = {"TWO,false", "ACE,true"})
    void hasAce(Denomination denomination, boolean expected) {
        cards.add(Card.from(Symbol.CLOVER, denomination));

        assertThat(cards.containsAce()).isEqualTo(expected);
    }
}