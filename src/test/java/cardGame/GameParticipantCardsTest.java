package cardGame;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

class GameParticipantCardsTest {

    @DisplayName("해당하는 유저가 Bust일 경우 true를 리턴한다.")
    @Test
    void isBustPlayer() {
        Player player = Player.joinGame("pola", new CardDeck().firstCardSettings());

        player.receiveCard(new Card(CardNumber.JACK, CardPattern.DIA_PATTERN));
        player.receiveCard(new Card(CardNumber.JACK, CardPattern.SPADE_PATTERN));

        Assertions.assertThat(player.isBust()).isTrue();
    }

    @DisplayName("해당하는 유저가 Bust가 아닐 경우 false를 리턴한다.")
    @Test
    void isNotBustPlayer() {
        Player player = Player.joinGame("pola", new CardDeck().firstCardSettings());

        Assertions.assertThat(player.isBust()).isFalse();
    }
}
