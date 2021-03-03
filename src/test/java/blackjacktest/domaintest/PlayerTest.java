package blackjacktest.domaintest;

import blackjack.domain.Card;
import blackjack.domain.Denomination;
import blackjack.domain.Player;
import blackjack.domain.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("pika");
    }

    @Test
    @DisplayName("플레이어 생성")
    void createPlayer() {
        assertThat(player).isEqualTo(new Player("pika"));
    }

    @Test
    @DisplayName("플레이어 카드 추가 성공")
    void receiveCard() {
        player.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        List<Card> cards = player.getCards();
        assertThat(cards.get(0)).isEqualTo(new Card(Shape.SPADE, Denomination.FOUR));
    }

    @Test
    @DisplayName("플레이어 카드 반환 성공")
    void getPlayerCards() {
        player.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        player.receiveCard(new Card(Shape.CLOVER, Denomination.THREE));
        player.receiveCard(new Card(Shape.HEART, Denomination.ACE));

        List<Card> cards = player.getCards();
        assertTrue(cards.containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }
}