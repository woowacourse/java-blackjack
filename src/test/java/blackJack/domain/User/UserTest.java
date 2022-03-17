package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Denomination;
import blackJack.domain.Card.Suit;
import blackJack.utils.ExeptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("소지한 카드가 21 이상일 경우 카드를 더이상 받지 못한다는 메세지를 반환한다.")
    void addCard() {
        Player player = new Player("test");
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        player.cards.add(new Card(Suit.HEART, Denomination.TEN));
        player.cards.add(new Card(Suit.HEART, Denomination.ACE));
        assertThatThrownBy(() -> player.requestCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.CANNOT_ADD_CARD);
    }
}