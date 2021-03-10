package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @DisplayName("Ace 점수 계산은 플레이어에게 유리하게 된다.")
    @Test
    void name() {
        List<Card> deck = Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.ACE)
        );
        Cards cards = new Cards();

        cards.add(deck.get(0));
        cards.add(deck.get(1));
        int score = cards.add(deck.get(2));

        assertThat(score).isEqualTo(13);
    }
}