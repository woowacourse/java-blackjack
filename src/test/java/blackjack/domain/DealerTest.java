package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import blackjack.domain.user.Dealer;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        List<Card> cardData = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            cardData.add(new Card(Shape.HEART, CardNumber.ACE));
        }
        assertThatThrownBy(() -> new Dealer(cardData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러는 카드 2장 이상을 갖고 있어야 합니다.");
    }

    @Test
    @DisplayName("현재 카드의 합이 16이하이면 카드를 더 받을 수 있다.")
    void canReceiveTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);
        assertThat(dealer.canReceive()).isTrue();
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 17이상이면 카드를 받지 못한다.")
    void cantReceiveTest1() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );
        final Dealer dealer = new Dealer(data);
        assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 버스트이면 카드를 받지 못한다.")
    void cantReceiveTest2() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);
        dealer.draw(new Card(Shape.HEART, CardNumber.TEN));
        assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    @DisplayName("딜러가 16 이하이면 카드를 한 장 받을 수 있다.")
    void dealerDrawTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );
        final Dealer dealer = new Dealer(data);

        assertThat(dealer)
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(2);

        final Card card = new Card(Shape.HEART, CardNumber.SIX);
        assertDoesNotThrow(() -> dealer.draw(card));

        assertThat(dealer)
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(3);
    }

    @Test
    @DisplayName("딜러가 17 이상일 때 카드를 주면 예외가 발생한다.")
    void dealerDrawExceptionTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );
        final Dealer dealer = new Dealer(data);

        assertThatThrownBy(() -> dealer.draw(new Card(Shape.HEART, CardNumber.ACE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 더이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러의 카드가 결론나면 카드를 얻을 수 있다.")
    void openCardsTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );
        final Dealer dealer = new Dealer(data);

        assertDoesNotThrow(() -> dealer.openCards());
    }


    @Test
    @DisplayName("딜러의 결과를 얻고 싶다면 카드를 줄 수 있을 때 까지 줘야한다.")
    void dealerCanReceiveResultTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SEVEN)
        );

        final Dealer dealer = new Dealer(data);
        assertDoesNotThrow(() -> dealer.getGamePoint());
    }


    @Test
    @DisplayName("딜러는 버스트가 나더라도 결과를 얻을 수 있다.(0이다)")
    void dealerCanReceiveResultWhenBustTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.TEN),
                new Card(Shape.HEART, CardNumber.SIX)
        );

        final Dealer dealer = new Dealer(data);
        dealer.draw(new Card(Shape.HEART, CardNumber.TEN));
        assertThat(dealer.getGamePoint())
                .extracting("gamePoint")
                .isEqualTo(0);
    }
}
