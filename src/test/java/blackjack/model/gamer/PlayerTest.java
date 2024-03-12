package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Player player = Player.from("ted");
        Card card = Card.of(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);
        List<Card> playerDeck = player.allCards();

        //then
        assertThat(playerDeck).containsExactly(card);
    }

    @DisplayName("플레이어가 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Player player = Player.from("ted");
        Card card = Card.of(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        player.receiveCard(card);

        //then
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Player player = Player.from("ted");
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = Card.of(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);
        int totalScore = player.totalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }
}
