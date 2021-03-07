package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("bada");
    }

    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자가 버스트인지 확인")
    void isBust() {
        player.receiveAdditionalCard(new Card(CardNumber.TEN, CardSuit.CLOVER));
        player.receiveAdditionalCard(new Card(CardNumber.NINE, CardSuit.HEART));
        player.receiveAdditionalCard(new Card(CardNumber.EIGHT, CardSuit.HEART));
        assertThat(player.isBust()).isTrue();
    }
}
