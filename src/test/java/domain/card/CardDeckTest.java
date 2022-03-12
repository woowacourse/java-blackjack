package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @Test
    @DisplayName("카드덱이 중복없이 생성됐는지 테스트")
    void checkDuplicate() {
        final List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            cards.add(CardDeck.draw());
        }

        int actualCount = (int) cards.stream().distinct().count();
        int expectedCount = cards.size();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("모든 카드가 소진됐을 경우 예외 발생")
    void checkException() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                CardDeck.draw();
            }
        })
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("모든 카드가 소진됐습니다. 게임을 재시작 해주세요.");
    }
}
