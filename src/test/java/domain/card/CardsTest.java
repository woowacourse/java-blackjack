package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("Cards 생성")
    void create() {
        assertThat(new Cards()).isInstanceOf(Cards.class);
    }

    @Test
    @DisplayName("카드 추가")
    void addCard() {
        Cards cards = new Cards();
        assertThat(cards.getSize()).isEqualTo(0);

        cards.addCard(Card.of("스페이드", "K"));
        assertThat(cards.getSize()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("점수 산출")
    @CsvSource(value = {"K,3,A:14", "K,Q,A:21", "A,10:21", "10,Q,3,A:24"}, delimiter = ':')
    void getScore(String input, int expected) {
        Cards cards = new Cards();
        String[] inputSymbols = input.split(",");

        for (String inputSymbol : inputSymbols) {
            cards.addCard(Card.of("스페이드", inputSymbol));
        }
        assertThat(cards.getScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭 여부 확인")
    void isBlackJack() {
        Cards cards = new Cards();

        cards.addCard(Card.of("스페이드", "K"));
        cards.addCard(Card.of("스페이드", "A"));
        assertThat(cards.getScore()).isEqualTo(21);
        assertThat(cards.getSize()).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("버스트 확인")
    @CsvSource(value = {"K,Q:False", "K,Q,2:True"}, delimiter = ':')
    void isBust(String input, Boolean expected) {
        Cards cards = new Cards();
        String[] inputSymbols = input.split(",");

        for (String inputSymbol : inputSymbols) {
            cards.addCard(Card.of("스페이드", inputSymbol));
        }
        assertThat(cards.isBust()).isEqualTo(expected);
    }
}
