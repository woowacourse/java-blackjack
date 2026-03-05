package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    Deck deck;

    @BeforeEach
    void beforeEach() {
        deck = new Deck();
    }

    @DisplayName("덱 생성 성공")
    @Test
    void 덱에_52장의_카드_생성() {
        deck.init();
        assertThat(deck.cards.size()).isEqualTo(52);
    }
}
