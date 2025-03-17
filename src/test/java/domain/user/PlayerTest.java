package domain.user;

import domain.Card;
import domain.CardDeck;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
    @Test
    void test() {
        // given
        String name = "수양";
        Player user = new Player(name);
        CardDeck cardDeck = new CardDeck();
        user.drawCard(cardDeck.drawCard());
        user.drawCard(cardDeck.drawCard());

        // when
        List<Card> cards = user.openInitialCard();

        // then
        Assertions.assertThat(cards).hasSize(2);
    }
}