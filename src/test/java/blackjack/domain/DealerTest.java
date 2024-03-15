package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.state.InitialState;
import blackjack.domain.state.StandState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러는 생성시 초기 상태를 갖는다")
    @Test
    public void createInitialStateDealer() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));

        assertThat(dealer.getState()).isInstanceOf(InitialState.class);
    }

    @DisplayName("딜러는 카드를 뽑으면 새로운 상태를 가진 딜러가 반환된다")
    @Test
    public void draw() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));

        Dealer newDealer = dealer.draw(Deck.of(new CardFactory(), cards -> cards));
    }

    @DisplayName("딜러의 상태가 종료되지 않았다면 true를 반환한다")
    @Test
    public void canDrawTrue() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));

        assertThat(dealer.canDraw()).isTrue();
    }

    @DisplayName("딜러의 상태가 종료되었다면 false를 반환한다")
    @Test
    public void canDrawFalse() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));
        Deck deck = Deck.of(new CardFactory(), cards -> cards);
        dealer = dealer.draw(deck); // 2장 초기화 KING, QUEEN -> 20

        Dealer newDealer = dealer.draw(deck); // KING, QUEEN, JACK -> 30

        assertThat(newDealer.canDraw()).isFalse();
    }

    @DisplayName("딜러가 스탠드를 하면 새로운 상태를 가진 딜러를 반환한다")
    @Test
    public void stand() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));
        Deck deck = Deck.of(new CardFactory(), cards -> cards);
        dealer = dealer.draw(deck);

        Dealer newDealer = dealer.stand();

        assertThat(newDealer.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("딜러의 스코어를 계산한다")
    @Test
    public void calculate() {
        Dealer dealer = Dealer.createInitialStateDealer(new Name("딜러"));
        Deck deck = Deck.of(new CardFactory(), cards -> cards);
        dealer = dealer.draw(deck);

        assertThat(dealer.calculateHand()).isEqualTo(Score.from(20));
    }
}
