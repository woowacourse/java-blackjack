package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 정상적으로 생성되는지 확인")
    void create() {
        Player player = new Player("승팡");

        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받는지 확인")
    void receiveCard() {
        Player player = new Player("필즈");
        Card card = new Card(Denomination.ACE, Suit.SPADE);

        player.receiveCard(card);

        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    @DisplayName("플레이어를 생성할때 이름이 null을 포함한채 생성하면 예외발생")
    void validateNameIsNull() {
        Hand cardHand = new Hand();

        assertThatThrownBy(() -> new Player(null, cardHand))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 이름과 카드패가 null일 수 없습니다.");
    }
}
