package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private final List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cards.add(new Card(Shape.DIAMOND, Value.TWO));
        cards.add(new Card(Shape.CLOVER, Value.JACK));
    }

    @Test
    @DisplayName("새로운 카드를 추가한다.")
    void pickNewCard() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.DIAMOND, Value.NINE));

        assertThat(player.getCards().getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘기면 bust 이다.")
    void bustPlayer() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.HEART, Value.QUEEN));

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 이하이면 bust 가 아니다.")
    void notBustPlayer() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.HEART, Value.ACE));

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 이상이면 더 이상 카드를 받지 못한다.")
    void noMoreCard() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.HEART, Value.NINE));

        assertThat(player.isMoreCardAble()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 점수가 20점 이하이면 카드를 더 받을 수 있다.")
    void isMoreCardAble() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.HEART, Value.FOUR));

        assertThat(player.isMoreCardAble()).isTrue();
    }

}
