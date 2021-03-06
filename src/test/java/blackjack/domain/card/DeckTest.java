package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
            new Card(Type.DIAMOND, Denomination.FIVE),
            new Card(Type.SPADE, Denomination.QUEEN)
    ));

    @DisplayName("덱 생성 테스트")
    @Test
    void create() {
        Deck deck = new Deck(cards);

        assertThat(deck).isNotNull();
    }

    @DisplayName("덱에서 카드를 1장 가져온다.")
    @Test
    void getCard() {
        Deck deck = new Deck(cards);

        assertThat(deck.draw()).isEqualTo(new Card(Type.DIAMOND, Denomination.FIVE));
        assertThat(deck.draw()).isEqualTo(new Card(Type.SPADE, Denomination.QUEEN));
    }
}
