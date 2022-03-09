package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 패 생성자 테스트")
    @Test
    void create() {
        List<Card> cardHand = List.of(Card.from(Number.ACE, Kind.HEART), Card.from(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);

        assertThat(cards).isNotNull();
    }
}
