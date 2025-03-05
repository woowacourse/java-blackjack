package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.")
    @Test
    void test1() {
        Cards cards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Player player = new Player("danny", cards);
        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);

        player.addCard(cardToAdd);

        assertThat(cards.getCards()).contains(cardToAdd);
    }

    @DisplayName("21을 넘었을 때 카드를 뽑으려하면 예외를 발생시킨다.")
    @Test
    void test2() {
        Cards cards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.FIVE, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE)))
        );

        Player player = new Player("danny", cards);
        Card cardToAdd = new Card(CardNumber.JACK, CardShape.SPADE);

        assertThatThrownBy(() -> player.addCard(cardToAdd));
    }
}
