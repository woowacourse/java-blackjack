package blackjacktest.domaintest.gamertest;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("pika");
    }

    @Test
    @DisplayName("플레이어 생성 성공")
    void createPlayerSucceed() {
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어 생성 실패")
    void createPlayerFail() {
        assertThatThrownBy(() -> new Player("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 카드 추가 성공")
    void receiveCard() {
        player.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        Cards cards = player.getTakenCards();
        assertThat(cards.peekCard()).isEqualTo(new Card(Shape.SPADE, Denomination.FOUR));
    }

    @Test
    @DisplayName("플레이어 카드 반환 성공")
    void getPlayerCards() {
        player.receiveCard(new Card(Shape.SPADE, Denomination.FOUR));
        player.receiveCard(new Card(Shape.CLOVER, Denomination.THREE));
        player.receiveCard(new Card(Shape.HEART, Denomination.ACE));

        Cards cards = player.getTakenCards();
        assertTrue(cards.getCards().containsAll(Arrays.asList(new Card(Shape.SPADE, Denomination.FOUR),
                new Card(Shape.CLOVER, Denomination.THREE),
                new Card(Shape.HEART, Denomination.ACE))));
    }
}