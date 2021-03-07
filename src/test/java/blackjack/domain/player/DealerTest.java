package blackjack.domain.player;

import blackjack.domain.result.Result;
import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("카드 받기 테스트")
    @Test
    void drawCard() {
        Dealer dealer = new Dealer();
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.SPADE, Denomination.QUEEN)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck);
        assertThat(dealer.getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
    }
}
