package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @DisplayName("카드 받기 테스트")
    @Test
    void draw() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.SPADE, Denomination.QUEEN)
        ));

        player.draw(new Deck(cards));

        assertThat(player.getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
    }

    @DisplayName("게임 시작 시 받은 패를 확인한다.")
    @Test
    void initializeDraw() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.SPADE, Denomination.TWO)
        ));

        player.initializeDraw(new Deck(cards));

        assertThat(player.getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
        assertThat(player.getCard(1)).isEqualTo(new Card(Type.SPADE, Denomination.TWO));
    }
}
