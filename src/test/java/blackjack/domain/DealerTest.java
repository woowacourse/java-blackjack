package blackjack.domain;

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
                new Card(Shape.HEART, CardNumber.of(1)),
                new Card(Shape.HEART, CardNumber.of(1))
        );
        defaultDealer = new Dealer(data);
    }

    @ParameterizedTest
    @DisplayName("딜러는 생성시에 카드 2장을 받지 않으면 예외가 발생한다.")
    @ValueSource(ints = {0, 1, 3, 4, 5, 6, 7})
    void dealerConstructTest(int value) {
        List<Card> cardData = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            cardData.add(new Card(Shape.HEART, CardNumber.of(1)));
        }
        assertThatThrownBy(() -> new Dealer(cardData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 카드 2장 이상을 갖고 있어야 합니다.");
    }

    @Test
    @DisplayName("현재 카드의 합이 16이하이면 카드를 더 받을 수 있다.")
    void canReceiveTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(6))
        );
        final Dealer dealer = new Dealer(data);
        assertThat(dealer.canReceive()).isTrue();
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 17이상이면 카드를 받지 못한다.")
    void cantReceiveTest1() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(7))
        );
        final Dealer dealer = new Dealer(data);
        assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    @DisplayName("현재 딜러의 카드합이 버스트이면 카드를 받지 못한다.")
    void cantReceiveTest2() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(10))
        );
        final Dealer dealer = new Dealer(data);
        dealer.draw(new Card(Shape.HEART, CardNumber.of(2)));
        assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    @DisplayName("딜러가 16 이하이면 카드를 한 장 받을 수 있다.")
    void dealerDrawTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(6))
        );
        final Dealer dealer = new Dealer(data);

        assertThat(dealer)
                .extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(2);

        final Card card = new Card(Shape.HEART, CardNumber.of(6));
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
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(7))
        );
        final Dealer dealer = new Dealer(data);

        assertThatThrownBy(() -> dealer.draw(new Card(Shape.HEART, CardNumber.of(1))))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 더이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러는 카드를 받을 수 있을 때 까지 카드를 받아야한다.")
    void needCardTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(6))
        );

        final Dealer dealer = new Dealer(data);
        assertThat(dealer.needCard()).isTrue();

        dealer.draw(new Card(Shape.HEART, CardNumber.of(1)));
        assertThat(dealer.needCard()).isFalse();
    }

    @Test
    @DisplayName("딜러의 결과를 얻고 싶다면 카드를 줄 수 있을 때 까지 줘야한다.")
    void dealerCantReceiveResultTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(6))
        );

        final Dealer dealer = new Dealer(data);
        assertThatThrownBy(() -> dealer.getGamePoint())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 17이상 혹은 버스트가 날 때 까지 카드를 줘야 결과를 알 수 있습니다.");
    }

    @Test
    @DisplayName("딜러의 결과를 얻고 싶다면 카드를 줄 수 있을 때 까지 줘야한다.")
    void dealerCanReceiveResultTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(7))
        );

        final Dealer dealer = new Dealer(data);
        assertDoesNotThrow(() -> dealer.getGamePoint());
    }


    @Test
    @DisplayName("딜러는 버스트가 나더라도 결과를 얻을 수 있다.(0이다)")
    void dealerCanReceiveResultWhenBustTest() {
        final List<Card> data = List.of(
                new Card(Shape.HEART, CardNumber.of(10)),
                new Card(Shape.HEART, CardNumber.of(6))
        );

        final Dealer dealer = new Dealer(data);
        dealer.draw(new Card(Shape.HEART, CardNumber.of(10)));
        assertThat(dealer.getGamePoint())
                .extracting("gamePoint")
                .isEqualTo(0);
    }
}

// 1. 딜러는 카드를 두 장 가지고 있다.
// 2. 딜러가 카드를 못 받는 것은 합이 17 이상이거나 버스트인 경우이다.
// 3. 딜러의 점수를 묻기 위해서(게임이 끝나는 조건)는 딜러의 카드 합이 17 이상이거나 버스트여야 한다.
