package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest(name = "[{index}] name \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어 이름이 공백일 경우 예외를 발생시킨다.")
    void test(String name) {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = Arrays.asList(cardDeck.getCard(1), cardDeck.getCard(2));
        Assertions.assertThatThrownBy(() -> new Player(name, cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest(name = "[{index}] 카드 size {0}")
    @ValueSource(ints = {1, 3, 4})
    @DisplayName("플레이어 이름이 공백일 경우 예외를 발생시킨다.")
    void test(int size) {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(cardDeck.getCard(i));
        }
        Assertions.assertThatThrownBy(() -> new Player("엘리", cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 가장 처음에는 카드를 2장씩 나눠줘야 합니다.");
    }
}
