package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @Test
    @DisplayName("이름이 딜러인 dealer 객체를 생성한다")
    void createDealerNameTest() {
        Dealer dealer = new Dealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러를 생성하면 빈 카드들이 생성된다.")
    void createDealerTest() {
        Dealer dealer = new Dealer();

        assertThat(dealer.getAllCards()).isEmpty();
    }

    @Test
    @DisplayName("카드를 받아 딜러 카드에 추가한다")
    void addDealerCardsTest() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        dealer.addCard(card);

        assertThat(dealer.getAllCards()).contains(new Card(CardSuit.HEART, CardNumber.ACE));
    }

    @Test
    @DisplayName("딜러는 처음에 카드를 한 장만 보여준다")
    void showFirstCardTest() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardSuit.HEART, CardNumber.ACE);
        Card card2 = new Card(CardSuit.SPADE, CardNumber.ACE);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.getFirstCard()).containsExactly(new Card(CardSuit.HEART, CardNumber.ACE));
    }

    @ParameterizedTest
    @MethodSource("dealerIsDrawableTestSource")
    @DisplayName("딜러 점수 상태에 따라 카드를 뽑을 수 있는지 결정된다")
    void isDrawableTest(List<Card> cards, boolean expect) {
        Dealer dealer = new Dealer();
        dealer.addCards(cards);

        boolean drawable = dealer.isDrawable();

        assertThat(drawable).isEqualTo(expect);
    }

    static Stream<Arguments> dealerIsDrawableTestSource() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.SIX)
                        ), true),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.HEART, CardNumber.SEVEN)
                        ), false),
                Arguments.of(
                        List.of(
                                new Card(CardSuit.HEART, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.TEN),
                                new Card(CardSuit.SPADE, CardNumber.TWO)
                        ), false)
        );
    }
}
