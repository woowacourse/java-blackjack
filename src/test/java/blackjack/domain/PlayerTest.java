package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
    }

    @Test
    @DisplayName("플레이어 생성")
    void create() {
        Player root = new Player("root", deck);
        assertThat(root).isEqualTo(new Player("root", deck));
    }

    @Test
    @DisplayName("이름이 공백인 경우 검증")
    void validate() {
        assertThatThrownBy(() -> new Player("", deck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("공백은 이름으로 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("카드 한 장 뽑는 기능")
    void drawCard() {
        Player root = new Player("root", deck);

        root.drawCard(deck);
        assertThat(root.getHand()).isEqualToComparingFieldByField(
            new Hand(Arrays.asList(Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.EIGHT),
                Card.valueOf(Shape.DIAMOND, CardValue.ACE))));
    }

    @Test
    @DisplayName("유저가 카드를 계속 더 받을건지 입력")
    void willContinue() {
        Player root = new Player("root", deck);
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.SPADE, CardValue.ACE)));

        root.willContinue(Response.getResponse("y"), deck);
        assertThat(root.isContinue()).isTrue();

        root.willContinue(Response.getResponse("n"), deck);
        assertThat(root.isContinue()).isFalse();

        assertThatThrownBy(() ->
            root.willContinue(Response.getResponse("x"), deck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("불가능한 입력 입니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        Dealer dealer = new Dealer(deck);
        Player pobi = new Player("pobi", deck);
        Player jason = new Player("jason", deck);
        Player root = new Player("root", deck);

        assertThat(pobi.match(dealer)).isEqualTo(ResultType.WIN);
        assertThat(jason.match(dealer)).isEqualTo(ResultType.TIE);
        assertThat(root.match(dealer)).isEqualTo(ResultType.LOSE);
    }
}
