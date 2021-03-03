package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
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
        root.willContinue("y");
        assertThat(root.isContinue()).isTrue();

        root.willContinue("n");
        assertThat(root.isContinue()).isFalse();

        assertThatThrownBy(() ->
            root.willContinue("x"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("불가능한 입력 입니다.");
    }
}
