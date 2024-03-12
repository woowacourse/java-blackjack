package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.generator.CardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("플레이어가 카드 한장을 지급받는다")
    @Test
    void addCard() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );

        Player player = new Player("daon", new Betting(0));
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
        Player player = new Player("daon", new Betting(0));
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
        Player player = new Player("daon", new Betting(0));
        player.addCards(given);

        assertThat(player.checkCanGetMoreCard()).isFalse();
    }
}
