package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("중복 카드 패 생성자 테스트")
    @Test
    void createDuplicate() {
        List<Card> cardHand = List.of(Card.from(Number.ACE, Kind.HEART), Card.from(Number.ACE, Kind.HEART));

        assertThatThrownBy(() -> new Cards(cardHand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 패에 중복된 카드가 존재할 수 없습니다.");
    }

    @DisplayName("카드 패에 카드 추가 테스트")
    @Test
    void receive() {
        List<Card> cardHand = List.of(Card.from(Number.ACE, Kind.HEART), Card.from(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);
        cards.receive(Card.from(Number.JACK, Kind.SPADE));

        assertThat(cards.getCards().size()).isEqualTo(3);
    }

    @DisplayName("중복 카드 추가 테스트")
    @Test
    void receiveDuplicate() {
        List<Card> cardHand = List.of(Card.from(Number.ACE, Kind.HEART), Card.from(Number.TWO, Kind.HEART));
        Cards cards = new Cards(cardHand);

        assertThatThrownBy(() -> cards.receive(Card.from(Number.ACE, Kind.HEART)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 패에 중복된 카드가 존재할 수 없습니다.");
    }
}
