package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hands;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("Player 생성 성공")
    @Test
    void create_success() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        cards.add(Card.of(Suit.CLUB, Denomination.EIGHT));
        Hands hands = new Hands(cards);
        assertThatCode(() -> new Player("pk", 1000, hands))
                .doesNotThrowAnyException();
    }

    @DisplayName("Player 생성 실패 : 베팅 금액 음수")
    @Test
    void create_fail() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.HEART, Denomination.EIGHT));
        cards.add(Card.of(Suit.CLUB, Denomination.EIGHT));
        Hands hands = new Hands(cards);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Player("pk", -1000, hands))
                .withMessageContaining("음수");
    }
}