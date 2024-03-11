package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.generator.CardGenerator;
import blackjack.view.PlayerResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {
    @DisplayName("플레이어가 카드 한장을 지급받는다")
    @Test
    void addCard() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Player player = new Player("daon");
        player.addCards(given);
        CardGenerator cardGenerator = new CardGenerator(maxRange -> 3);

        player.addCard(cardGenerator.drawCard());
        assertThat(player.getCards().getCards()).hasSize(3);
    }

    @DisplayName("카드를 추가로 받을 수 있는지 확인한다")
    @Test
    void checkDrawCardState() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Player player = new Player("daon");
        player.addCards(given);

        assertThat(player.checkCanGetMoreCard()).isTrue();
    }

    @DisplayName("카드를 추가로 받을 수 있는지 확인한다")
    @Test
    void checkDrawCardStateOverWinningScore() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.SPADE)
        );
        Player player = new Player("daon");
        player.addCards(given);

        assertThat(player.checkCanGetMoreCard()).isFalse();
    }

    @DisplayName("딜러와 비교하여 승패를 겨룬다.")
    @ParameterizedTest
    @CsvSource(value = {"SEVEN,PUSH", "SIX,LOSE", "EIGHT,WIN"})
    void determineWinner(CardNumber givenNumber, PlayerResultStatus expected) {
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

        assertThat(player.determineWinner(dealer)).isEqualTo(expected);
    }
}
