package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.Result;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
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

        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        int cardCount = player.cards()
                .getCards().size();

        assertThat(cardCount).isEqualTo(2);
    }

    @DisplayName("플레이어의 결과를 산출한다. - 버스트여서 패")
    @Test
    public void decideBustUserLose1() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        Result result = player.produceResult(dealer);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어의 결과를 산출한다. - 버스트 아니고 패")
    @Test
    public void decideBustUserLose2() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        Result result = player.produceResult(dealer);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어의 결과를 산출한다. - 무승부")
    @Test
    public void decideBustUserStandOff() {
        Dealer dealer = new Dealer();
        Player player = new Player("amazzi");
        dealer.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.EIGHT),
                new Card(Suit.CLOVER, Denomination.KING)
        )));

        Result result = player.produceResult(dealer);

        assertThat(result).isEqualTo(Result.STAND_OFF);
    }

    @DisplayName("받은 카드에 따라 상태를 확인한다. - Hit")
    @Test
    public void receiveCardsState() {
        Player player = new Player("amazzi");
        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
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
        player.receiveCards(new Cards(Arrays.asList(
                new Card(Suit.SPACE, Denomination.JACK),
                new Card(Suit.CLOVER, Denomination.TWO)
        )));

        player.hit(new Card(Suit.HEART, Denomination.JACK));
        State state = player.getState();

        assertThat(state).isInstanceOf(Bust.class);
    }
}
