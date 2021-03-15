package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.hand.Hand;
import blackjack.domain.state.hand.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("pobi"), Money.of(1));
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );
        player.receiveFirstHand(cards);
    }

    @Test
    @DisplayName("플레이어 이름 반환 받는다.")
    void getNameTest() {
        player = new Player(new Name("pobi"), Money.of(1));

        String name = player.getName();

        assertThat(name).isEqualTo("pobi");
    }

    @Test
    @DisplayName("플레이어의 손패가 한계 점수인지 잘 판단한다.")
    void isOverLimitScoreTest() {
        assertThat(player.isOverLimitScore()).isFalse();

        player.receiveCard(new Card(Pattern.DIAMOND, Number.TEN));

        assertThat(player.isOverLimitScore()).isTrue();
    }

    @Test
    @DisplayName("플레이어는 카드를 받는다.")
    void testReceiveCard() {
        assertThat(player.getTotalScore()).isEqualTo(new Score(20));
    }

    @Test
    @DisplayName("파라미터 없는 profit 메서드 잘 반환된다.")
    void profitTest() {
        /*given*/
        player = new Player(new Name("pobi"), Money.of(1000));
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );

        /*when*/
        player.receiveFirstHand(cards);
        player.stay();
        double profit = player.profit();

        /*then*/
        assertThat(profit).isEqualTo(1000.0d);
    }

    @Test
    @DisplayName("배당율을 직접 입력하는 profit 메서드 배당금 잘 반환해준다.")
    void profitTest2() {
        /*given*/
        player = new Player(new Name("pobi"), Money.of(1000));
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );

        /*when*/
        player.receiveFirstHand(cards);
        double profit = player.profit(2.0d);

        /*then*/
        assertThat(profit).isEqualTo(2000.0d);
    }

    @Test
    @DisplayName("플레이어의 손패 반환 받는다.")
    void getHandTest() {
        /*given*/
        player = new Player(new Name("pobi"), Money.of(1000));
        List<Card> cards = Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );

        /*when*/
        player.receiveFirstHand(cards);
        Hand hand = player.hand();

        /*then*/
        assertThat(hand).isInstanceOf(Hand.class);
        assertThat(hand.toList()).containsExactly(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.DIAMOND, Number.TEN)
        );
    }
}
