package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어를 정상 생성한다.")
    void createUser() {
        assertThatCode(() -> new Player("slow", new Cards(firstDrawTwoCards())))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 처음 받은 카드 두 장을 모두 보여준다.")
    void openFirstCards() {
        final List<Card> firstDrawTwoCards = firstDrawTwoCards();
        Player player = new Player("slow", new Cards(firstDrawTwoCards));

        final List<Card> actual = player.openFirstCards();

        assertThat(actual).isEqualTo(firstDrawTwoCards);
    }

    @Test
    @DisplayName("플레이어는 카드를 받아서 본인의 카드 리스트에 추가할 수 있다.")
    void receiveCard() {
        Cards cards = new Cards(firstDrawTwoCards());
        Player player = new Player("slow", cards);
        final int expected = 3;

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SIX));
        final int actual = player.cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }

    private List<Card> firstDrawTwoCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardPattern.HEART, CardNumber.JACK));
        cards.add(new Card(CardPattern.CLOVER, CardNumber.KING));
        return cards;
    }

    @Test
    @DisplayName("가지고 있는 모든 카드를 보여준다.")
    void openAllOfCards() {
        Cards cards = new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.JACK),
                new Card(CardPattern.SPADE, CardNumber.FIVE),
                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                new Card(CardPattern.CLOVER, CardNumber.TWO)
        ));
        Player player = new Player("slow", cards);
        final List<Card> expected = cards.getCards();

        final List<Card> actual = player.openAllOfCards();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 카드의 총합이 21이하일 경우, 카드를 더 받을 수 있다.")
    void possibleToReceivedCard() {
        Cards cards = new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.ACE)
        ));
        Player player = new Player("slow", cards);
        final boolean expected = true;

        final boolean actual = player.isPossibleToReceiveCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 카드의 총합이 21을 초과할 경우, 카드를 더 받을 수 없다.")
    void impossibleToReceivedCard() {
        Cards cards = new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.TWO)
        ));
        Player player = new Player("slow", cards);
        final boolean expected = false;

        final boolean actual = player.isPossibleToReceiveCard();

        assertThat(actual).isEqualTo(expected);
    }
}
