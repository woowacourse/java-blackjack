package cardGame;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

class SingleMatchTest {

    @DisplayName("Player가 21을 넘는 카드를 가진다는 것을 확인한다.")
    @Test
    void isCanPlayPlayer() {
        Player player = Player.joinGame("pola", new CardDeck());
        player.receiveCard(new Card(CardNumber.JACK, CardPattern.CLOVER_PATTERN));
        player.receiveCard(new Card(CardNumber.KING, CardPattern.CLOVER_PATTERN));

        SingleMatch singleMatch = new SingleMatch(player);
        Assertions.assertThat(singleMatch.isBustPlayer()).isTrue();
    }

    @DisplayName("Player가 21을 넘지 않는 카드를 가지지 않는 다는 것을 확인한다.")
    @Test
    void isCanNotPlayPlayer() {
        Player player = Player.joinGame("pola", new CardDeck());
        SingleMatch singleMatch = new SingleMatch(player);
        Assertions.assertThat(singleMatch.isBustPlayer()).isFalse();
    }
}
