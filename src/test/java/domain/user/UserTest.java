package domain.user;

import domain.CardDeck;
import domain.BlackjackGame;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    @DisplayName("카드를 뽑았을 때 21을 넘기면 버스트된다")
    @Test
    void test3() {
        // given
        User user = new Player("수양");
        CardDeck cardDeck = new CardDeck();
        user.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());
        for (int i = 0; i < 12; i++) {
            user.drawCard(cardDeck.drawCard());
        }

        // when && then
        Assertions.assertThat(user.isBust()).isEqualTo(true);
    }
}