package blackjack.domain;

import static org.assertj.core.api.Assertions.*;


import blackjack.domain.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import jdk.jshell.spi.ExecutionControl.RunException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("카드 중복생성 안되는지 테스트")
    @Test
    void duplicatedCardTest() {
        List<Card> cards = new ArrayList<>();
        boolean duplicatedCardExist = false;
        for (int i = 0; i<48; ++i) {
            Card card = Card.randomPick(new RandomNumberGenerator());
            duplicatedCardExist |= cards.contains(card);
            cards.add(card);
        }
        assertThat(duplicatedCardExist).isFalse();
    }

    @DisplayName("카드 초과생성 방지기능 테스트")
    @Test
    void cardConstructOverLimitTest() {
        for (int i = 0; i<48; ++i) {
            Card.randomPick(new RandomNumberGenerator());
        }
        assertThatThrownBy(() -> Card.randomPick(new RandomNumberGenerator()))
                .isInstanceOf(RuntimeException.class);
    }
}
