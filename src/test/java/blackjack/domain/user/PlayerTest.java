package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @DisplayName("User 객체를 생성한다.")
    @Test
    public void createUser() {
        Player player = new Player("amazzi");

        assertThat(player).isInstanceOf(Player.class);
    }

    @DisplayName("카드를 두장 분배받는다.")
    @Test
    public void distributeTwoCards() {
        Player player = new Player("amazzi");

        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.DIAMOND, Denomination.JACK)
        )));
        int cardCount = player.cards().getCards().size();

        assertThat(cardCount).isEqualTo(2);
    }

    @DisplayName("카드 합계가 21 이하인지 확인한다. - 카드를 더 받을 수 있다.")
    @Test
    public void isDrawableTrue() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.DIAMOND, Denomination.JACK)
        )));

        boolean isAbleToHit = player.isAbleToHit();

        assertThat(isAbleToHit).isTrue();
    }

    @DisplayName("카드 합계가 21 초과인지 확인한다. - 카드를 더 받을 수 없다.")
    @Test
    public void isDrawableFalse() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.DIAMOND, Denomination.JACK)
        )));

        boolean isAbleToHit = player.isAbleToHit();

        assertThat(isAbleToHit).isTrue();
    }

    @DisplayName("카드를 공개한다.")
    @Test
    void show() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        int cardCount = player.cards()
                .getCards().size();

        assertThat(cardCount).isEqualTo(2);
    }

    @DisplayName("받은 카드에 따라 상태를 확인한다. - Hit")
    @Test
    public void receiveCardsState() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.TWO),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        State state = player.getState();

        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("받은 카드에 따라 상태를 확인한다. - blackjack")
    @Test
    public void receiveCardsState2() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        State state = player.getState();

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("상태에 따라 카드를 받는다. - hit")
    void draw1() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        player.hit(new Card(Suit.SPACE, Denomination.TWO));
        State state = player.getState();

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("상태에 따라 카드를 받는다. - bust")
    void draw2() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        player.hit(new Card(Suit.HEART, Denomination.JACK));
        State state = player.getState();

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드를 더이상 받지 않는다.")
    void stay() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        player.stay();
        State state = player.getState();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackjack() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));

        boolean isBlackjack = player.isBlackjack();

        assertThat(isBlackjack).isTrue();
    }

    @Test
    @DisplayName("버스트인지 확인한다.")
    void isBust() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));
        player.hit(new Card(Suit.HEART, Denomination.JACK));

        boolean isBust = player.isBust();

        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("수익을 구한다. - 플레이어 버스트")
    void calculateProfit() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));
        player.betMoney(new Money(1000));
        player.hit(new Card(Suit.HEART, Denomination.JACK));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.QUEEN)
        )));

        long profit = player.getProfit(dealer);

        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    @DisplayName("수익을 구한다. - 딜러, 플레이어 블랙잭인 경우")
    void calculateProfit2() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));
        player.betMoney(new Money(1000));
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.QUEEN)
        )));

        long profit = player.getProfit(dealer);

        assertThat(profit).isEqualTo(0);
    }

    @Test
    @DisplayName("수익을 구한다. - 플레이어 수가 더 큰 경우")
    void calculateProfit3() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));
        player.betMoney(new Money(1000));
        player.stay();
        Dealer dealer = new Dealer();
        dealer.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.SIX),
                new Card(Suit.CLOVER, Denomination.SIX)
        )));

        long profit = player.getProfit(dealer);

        assertThat(profit).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackJack() {
        Player player = new Player("amazzi");
        player.initializeCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.QUEEN),
                new Card(Suit.CLOVER, Denomination.JACK)
        )));
        player.betMoney(new Money(1000));

        boolean isBlackjack = player.isBlackJack();

        assertThat(isBlackjack).isFalse();
    }
}
