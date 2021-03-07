package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        player.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        player.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        player.receiveCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(player.isBust()).isTrue();
    }
}
