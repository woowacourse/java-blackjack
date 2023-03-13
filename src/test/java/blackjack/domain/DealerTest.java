package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealerTest {
    private Dealer defaultDealer;

    @BeforeEach
    void setting() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.ACE),
                new Card(Shape.HEART, CardNumber.ACE)
        );
        defaultDealer = new Dealer(data);
    }

    @ParameterizedTest
    @DisplayName("딜러는 생성시에 카드 2장을 받지 않으면 예외가 발생한다.")
    @ValueSource(ints = {0, 1, 3, 4, 5, 6, 7})
    void dealerConstructTest(int value) {
        //given
        List<Card> cardData = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            cardData.add(new Card(Shape.HEART, CardNumber.ACE));
        }

        //when,then
        assertThatThrownBy(() -> new Dealer(cardData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러 카드 두 장을 초기에 가지고 있어야 합니다.");
    }

    @Test
    @DisplayName("현재 카드의 합이 16이하이면 카드를 더 받을 수 있다.")
    void canReceiveTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);

        //when
        final boolean canReceive = dealer.canReceive();

        //then
        assertTrue(canReceive);
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 17이상이면 카드를 받지 못한다.")
    void cantReceiveTest1() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );
        final Dealer dealer = new Dealer(data);

        //when
        final boolean canReceive = dealer.canReceive();

        //then
        assertFalse(canReceive);
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 버스트이면 카드를 받지 못한다.")
    void cantReceiveTest2() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);
        dealer.draw(new Card(Shape.HEART, CardNumber.TEN));

        //when
        final boolean canReceive = dealer.canReceive();

        //then
        assertFalse(canReceive);
    }

    @Test
    @DisplayName("딜러가 16 이하이면 카드를 한 장 받을 수 있다.")
    void dealerDrawTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);

        final Cards dealerCards = dealer.getCards();
        final List<Card> dealerCardsData = dealerCards.getCards();

        assertThat(dealerCardsData.size())
                .isEqualTo(2);
        final Card card = new Card(Shape.HEART, CardNumber.SIX);

        //when
        dealer.draw(card);
        final Cards addedDealerCards = dealer.getCards();
        final List<Card> addedDealerCardsData = addedDealerCards.getCards();

        //then
        assertThat(addedDealerCardsData.size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 17 이상일 때 카드를 주면 예외가 발생한다.")
    void dealerDrawExceptionTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );
        final Dealer dealer = new Dealer(data);
        final Card additionalCard = new Card(Shape.HEART, CardNumber.ACE);

        //when, then
        assertThatThrownBy(() -> dealer.draw(additionalCard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 더이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 버스트가 나더라도 결과를 얻을 수 있다.(0이다)")
    void dealerCanReceiveResultWhenBustTest() {
        //given
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );

        final Dealer dealer = new Dealer(data);

        //when
        dealer.draw(new Card(Shape.HEART, CardNumber.TEN));

        //then
        assertThat(dealer.getGameScore().getValue())
                .isEqualTo(0);
    }
}
