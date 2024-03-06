package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("ted");
        CardProperties cardProperties = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        Card card = new Card(cardProperties);
        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.getDeck();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어가 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Player player = new Player("ted");
        CardProperties cardProperties = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        Card card = new Card(cardProperties);

        //when
        player.receiveCard(card);

        //then
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Player player = new Player("ted");
        CardProperties cardProperties1 = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        CardProperties cardProperties2 = new CardProperties(CardPattern.CLOVER, CardNumber.SEVEN);

        Card card = new Card(cardProperties1);
        Card card2 = new Card(cardProperties2);

        //when
        player.receiveCard(card);
        player.receiveCard(card2);
        int totalScore = player.calculateTotalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }
}
