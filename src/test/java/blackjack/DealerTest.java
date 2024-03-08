package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.CardNumber;
import blackjack.model.CardShape;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.ResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @DisplayName("카드를 추가발급받는다")
    @Test
    void addCardLessThan() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);

        Card card = new Card(CardNumber.ACE, CardShape.DIAMOND);
        dealer.addCard(card);

        assertThat(dealer.getCards().getCards()).hasSize(3);
    }

    @DisplayName("플레이어 점수와 비교하여 승패를 겨룬다.")
    @ParameterizedTest
    @CsvSource(value = {"SEVEN,PUSH", "SIX,LOSE", "EIGHT,WIN"})
    void determineWinner(CardNumber givenNumber, ResultStatus expected) {
        List<Card> given = List.of(
                new Card(CardNumber.SEVEN, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);
        Player player = new Player("daon");

        List<Card> playerCards = List.of(
                new Card(givenNumber, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        player.addCards(playerCards);

        assertThat(dealer.determineWinner(player)).isEqualTo(expected);
    }
}
