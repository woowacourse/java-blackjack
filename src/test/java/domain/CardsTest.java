package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(new ArrayList<>());
    }

    @Test
    @DisplayName("Cards 생성")
    void cards() {
        Cards cards = new Cards(Collections.singletonList(new Card(Symbol.TWO, Type.HEART)));
        assertThat(cards).isNotNull();
    }

    @Test
    @DisplayName("Cards에 카드 추가")
    void add() {
        Card card = new Card(Symbol.TEN, Type.DIAMOND);
        cards.add(card);
        assertThat(cards.getCards()).isEqualTo(Collections.singletonList(card));
    }

    @Test
    @DisplayName("Cards의 Score를 계산")
    void calculateScore() {
        cards.add(new Card(Symbol.TEN, Type.DIAMOND));
        cards.add(new Card(Symbol.FIVE, Type.DIAMOND));
        assertThat(cards.calculateScore()).isEqualTo(15);
    }
}
