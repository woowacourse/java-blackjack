package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("Dealer 객체를 생성한다.")
    @Test
    public void createDealer() {
        assertThat(dealer).isInstanceOf(Dealer.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void drawInitialCards() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));
        int cardSize = dealer.cards().getCards().size();

        assertThat(cardSize).isEqualTo(2);
    }

    @DisplayName("카드 합계가 16 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isRunningTrue() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.TWO),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isRunning()).isTrue();
    }

    @DisplayName("카드 합계가 17 이상인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isRunningFalse() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isRunning()).isFalse();
    }

    @DisplayName("카드 한장은 공개하고 한장은 숨긴다.")
    @Test
    void show() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.EIGHT),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.showOneCard()).isInstanceOf(Card.class);
    }

    @DisplayName("상태에 따라 카드를 더 받는다. - Hit")
    @Test
    void drawHit() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.THREE),
                new Card(Shape.CLOVER, Value.KING)
        )));
        dealer.hit(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Value.FIVE))));

        assertThat(dealer.state()).isInstanceOf(Hit.class);
    }

    @DisplayName("상태에 따라 카드를 더 받는다. - Bust")
    @Test
    void drawBust() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.THREE),
                new Card(Shape.CLOVER, Value.KING)
        )));
        dealer.hit(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Value.QUEEN))));

        assertThat(dealer.state()).isInstanceOf(Bust.class);
    }

    @DisplayName("상태를 확인한다. - Blackjack")
    @Test
    void isBlackjack() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.ACE),
                new Card(Shape.CLOVER, Value.KING)
        )));

        assertThat(dealer.isBlackjack()).isTrue();
    }

    @DisplayName("상태를 확인한다. - Bust")
    @Test
    void isBust() {
        dealer.drawInitialCards(new Cards(Arrays.asList(
                new Card(Shape.SPACE, Value.THREE),
                new Card(Shape.CLOVER, Value.KING)
        )));
        dealer.hit(new Cards(Collections.singletonList(
                new Card(Shape.CLOVER, Value.QUEEN))));

        assertThat(dealer.isBust()).isTrue();
    }
}
