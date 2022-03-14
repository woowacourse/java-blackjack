package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Number;
import blackJack.domain.Card.Shape;
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
        player.cards.add(new Card(Shape.HEART, Number.JACK));
        player.cards.add(new Card(Shape.HEART, Number.TEN));
        player.cards.add(new Card(Shape.HEART, Number.ACE));
        assertThatThrownBy(() -> player.requestAddCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExeptionMessage.CANNOT_ADD_CARD);
    }
}