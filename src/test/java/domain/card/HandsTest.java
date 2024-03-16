package domain.card;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @Test
    @DisplayName("Ace의 두 값 중 큰 값이 유리할 때는 큰 값을 쓴다")
    void sum() {
        final Hands hands = new Hands(new ArrayList<>());

        hands.add(new Card(Rank.ACE, Suit.CLUBS));
        hands.add(new Card(Rank.TEN, Suit.CLUBS));

        Assertions.assertThat(hands.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace의 두 값 중 작은 값이 유리할 때는 작은 값을 쓴다")
    void sum2() {
        final Hands hands = new Hands(new ArrayList<>());

        hands.add(new Card(Rank.TEN, Suit.CLUBS));
        hands.add(new Card(Rank.TEN, Suit.CLUBS));
        hands.add(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(hands.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace의 값은 큰 값이었다가 작아질 수 있다")
    void sum3() {
        final Hands hands = new Hands(new ArrayList<>());

        hands.add(new Card(Rank.ACE, Suit.CLUBS));
        hands.add(new Card(Rank.TEN, Suit.CLUBS));
        hands.add(new Card(Rank.TEN, Suit.CLUBS));
        hands.add(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(hands.calculateScore()).isEqualTo(22);
    }
}
