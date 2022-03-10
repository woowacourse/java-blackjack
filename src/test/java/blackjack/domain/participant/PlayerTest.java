package blackjack.domain.participant;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);
        player = Player.of("hudi", cardBundle);
    }

    @DisplayName("카드를 전달받아 cardBundle에 추가할 수 있다.")
    @Test
    void receiveCard() {
        player.receiveCard(CLOVER6);

        Set<Card> actual = player.getCardBundle().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("점수가 21을 넘지 않으면 true를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan21() {
        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21이면 true를 반환한다.")
    @Test
    void canReceive_returnTrueOn21() {
        player.receiveCard(CLOVER2);
        player.receiveCard(CLOVER10);

        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21을 초과하면 false를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan21() {
        player.receiveCard(CLOVER10);
        player.receiveCard(CLOVER_KING);

        boolean actual = player.canReceive();

        assertThat(actual).isFalse();
    }
}
