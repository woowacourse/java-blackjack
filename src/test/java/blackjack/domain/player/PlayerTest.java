package blackjack.domain.player;

import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    Player player;
    Deck deck;

    @BeforeEach
    void setUp() {
        player = new Player();
        deck = new Deck();
    }

    @DisplayName("카드 받기 테스트")
    @Test
    void draw() {
        player.draw(deck, 0);

        assertThat(player.cards().getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
    }

    @DisplayName("게임 시작 시 받은 패를 확인한다.")
    @Test
    void initializeDraw() {
        player.initializeDraw(deck, 0);

        assertThat(player.cards().getCard(0)).isEqualTo(new Card(Type.SPADE, Denomination.ACE));
        assertThat(player.cards().getCard(1)).isEqualTo(new Card(Type.SPADE, Denomination.TWO));
    }
}
