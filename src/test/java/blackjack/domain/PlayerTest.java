package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("플레이어 생성")
    @Test
    void create() {
        Player root = new Player("root");
        assertThat(root).isEqualTo(new Player("root"));
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() {
        Player root = new Player("root");
        Deck deck = new Deck(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));

        root.draw(deck);
        assertThat(root.getHand()).isEqualToComparingFieldByField(
                new Hand(Collections.singletonList(Card.valueOf(Shape.DIAMOND, CardValue.ACE))));
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() {
        Player root = new Player("root");

        root.willContinue("y");
        assertThat(root.isContinue()).isTrue();

        root.willContinue("n");
        assertThat(root.isContinue()).isFalse();

        assertThatThrownBy(() ->
                root.willContinue("x"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 입력 입니다.");
    }

    @Test
    @DisplayName("버스트가 없는 경우 승패 결과")
    void match() {
        assertThat(TestSetUp.WINNER.match(TestSetUp.DEALER)).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.TIE_PLAYER.match(TestSetUp.DEALER)).isEqualTo(ResultType.TIE);
        assertThat(TestSetUp.LOSER.match(TestSetUp.DEALER)).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("버스트인 경우 승패 결과")
    void matchWithBust() {
        assertThat(TestSetUp.BUST_PLAYER.match(TestSetUp.DEALER)).isEqualTo(ResultType.LOSE);
        assertThat(TestSetUp.BUST_PLAYER.match(TestSetUp.BUST_DEALER)).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트인 경우 승패 결과")
    void matchWithDealerBust() {
        assertThat(TestSetUp.WINNER.match(TestSetUp.BUST_DEALER)).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.LOSER.match(TestSetUp.BUST_DEALER)).isEqualTo(ResultType.WIN);
        assertThat(TestSetUp.BUST_PLAYER.match(TestSetUp.BUST_DEALER)).isEqualTo(ResultType.LOSE);
    }
}
