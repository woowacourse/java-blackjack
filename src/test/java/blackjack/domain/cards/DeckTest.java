package blackjack.domain.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱이 소진되었을 경우 검증")
    void notEnoughCards() {
        Deck deck = new Deck(new ArrayList<>());
        assertThatThrownBy(deck::draw)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("덱이 모두 소진되었습니다.");
    }

    @Test
    @DisplayName("카드가 순서대로 뽑아지는지 확인")
    void draw() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));
        assertThat(deck.draw()).isEqualTo(Card.valueOf(Shape.DIAMOND, CardValue.ACE));
        assertThat(deck.draw()).isEqualTo(Card.valueOf(Shape.SPADE, CardValue.ACE));
    }
}
