package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어 생성")
    @Test
    void create() {
        Player root = new Player("root");
        assertThat(root).isEqualTo(new Player("root"));
    }

    @DisplayName("이름이 공백인 경우 검증")
    @Test
    void validate() {
        assertThatThrownBy(() -> new Player(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("공백은 이름으로 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() {
        Player root = new Player("root");
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));

        root.drawCard(deck);
        assertThat(root.getHand()).isEqualToComparingFieldByField(
            new Hand(Arrays.asList(Card.valueOf(Shape.DIAMOND, CardValue.ACE))));
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() {
        Player root = new Player("root");
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));

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
    @DisplayName("승패 결과")
    void match() {
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        Player root = new Player("root");
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));

        dealer.drawCard(deck);
        dealer.drawCard(deck);

        pobi.drawCard(deck);
        pobi.drawCard(deck);

        jason.drawCard(deck);
        jason.drawCard(deck);

        root.drawCard(deck);
        root.drawCard(deck);

        assertThat(pobi.match(dealer)).isEqualTo(ResultType.WIN);
        assertThat(jason.match(dealer)).isEqualTo(ResultType.TIE);
        assertThat(root.match(dealer)).isEqualTo(ResultType.LOSE);
    }
}
