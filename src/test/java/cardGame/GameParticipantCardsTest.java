package cardGame;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import java.util.ArrayList;
import java.util.List;
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

    @DisplayName("카드 한개를 받을 수 있다.")
    @Test
    void receiveOneCard() {
        List<Card> cards = new ArrayList<>();

        GameParticipantCards gameParticipantCards = new GameParticipantCards(cards);

        Card card = new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN);

        gameParticipantCards.receiveCard(card);

        Assertions.assertThat(gameParticipantCards.getCards().countCard()).isEqualTo(1);
    }

    @DisplayName("최대 카드 점수를 가져온다")
    @Test
    void getMaxCardScore() {
        GameParticipantCards gameParticipantCards = new GameParticipantCards(
                List.of(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN),
                        new Card(CardNumber.FOUR, CardPattern.SPADE_PATTERN)));

        Assertions.assertThat(gameParticipantCards.getCardScore()).isEqualTo(15);
    }
}
