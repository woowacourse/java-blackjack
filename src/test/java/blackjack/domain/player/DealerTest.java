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

class DealerTest {

    @Test
    @DisplayName("딜러를 정상 생성 한다.")
    void createDealer() {
        assertThatCode(() -> new Dealer(new Cards(firstDrawTwoCards())))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 처음 받은 카드 중 하나만 보여준다.")
    void openFirstCards() {
        final List<Card> firstDrawTwoCards = firstDrawTwoCards();
        Dealer dealer = new Dealer(new Cards(firstDrawTwoCards));
        final Card expected = firstDrawTwoCards.get(0);

        final Card actual = dealer.openFirstCards().get(0);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 카드를 받아서 본인의 카드 리스트에 추가할 수 있다.")
    void receiveCard() {
        Cards cards = new Cards(firstDrawTwoCards());
        Dealer dealer = new Dealer(cards);
        final int expected = 3;

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SIX));
        final int actual = dealer.cards.getCards().size();

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
        Dealer dealer = new Dealer(cards);
        final List<Card> expected = cards.getCards();

        final List<Card> actual = dealer.openAllOfCards();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16이하일 경우, 카드를 더 받을 수 있다.")
    void possibleToReceivedCard() {
        Cards cards = new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.SIX)
        ));
        Dealer dealer = new Dealer(cards);
        final boolean expected = true;

        final boolean actual = dealer.isPossibleToReceiveCard();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16을 초과할 경우, 카드를 더 받을 수 없다.")
    void impossibleToReceivedCard() {
        Cards cards = new Cards(Arrays.asList(
                new Card(CardPattern.HEART, CardNumber.TEN),
                new Card(CardPattern.SPADE, CardNumber.SEVEN)
        ));
        Dealer dealer = new Dealer(cards);
        final boolean expected = false;

        final boolean actual = dealer.isPossibleToReceiveCard();

        assertThat(actual).isEqualTo(expected);
    }
}
