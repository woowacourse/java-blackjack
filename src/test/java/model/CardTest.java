package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드의 숫자 계산은 카드 문양이 아닌 카드 숫자로 한다.")
    @Test
    void test1() {
        Cards cards = new Cards(Set.of(
                new Card(CardNumber.THREE, "diamond"),
                new Card(CardNumber.NINE, "clover"),
                new Card(CardNumber.EIGHT, "diamond")
        ));
        assertThat(cards.calculateSum()).isEqualTo(20);
    }

    @DisplayName("Ace는 카드의 합이 21을 초과하기 전까지는 11로 계산한다.")
    @Test
    void test2() {
        Cards cards = new Cards(Set.of(
                new Card(CardNumber.ACE_ELEVEN, "diamond"),
                new Card(CardNumber.NINE, "clover")
        ));

        assertThat(cards.calculateSum()).isEqualTo(20);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void test3() {
        Cards cards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.ACE_ELEVEN, "diamond"),
                new Card(CardNumber.NINE, "clover"),
                new Card(CardNumber.NINE, "spade")
        )));

        assertThat(cards.calculateSum()).isEqualTo(19);
    }

    @DisplayName("예외로 Ace는 카드의 합이 21을 초과하면 1로 계산한다.")
    @Test
    void test4() {
        Cards cards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.ACE_ELEVEN, "diamond"),
                new Card(CardNumber.ACE_ELEVEN, "spade"),
                new Card(CardNumber.KING, "clover"),
                new Card(CardNumber.QUEEN, "spade")
        )));

        assertThat(cards.calculateSum()).isEqualTo(22);
    }
}
