package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 잘 생성된다.")
    void playerConstructSuccessTest() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Player(new Name("이름")));
    }

    @Test
    @DisplayName("플레이어가 패를 받는다.")
    void receiveDeckTest() {
        Player player = new Player(new Name("이름"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );
        player.receiveHands(hands);

        assertThat(player.getHands().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 더 받을 수 있다.")
    void isPlayerNotOverTest() {
        Player player = new Player(new Name("이름"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );
        player.receiveHands(hands);
        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 카드를 잘 받는다.")
    void playerReceiveCardTest() {
        Player player = new Player(new Name("이름"));
        Assertions.assertThatNoException()
                .isThrownBy(() -> player.hit(new Card(Shape.HEART, Rank.ACE)));
    }

    @Test
    @DisplayName("플레이어 점수를 계산한다.")
    void calculateScoreTest() {
        Player player = new Player(new Name("이름"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );

        player.receiveHands(hands);
        int result = player.calculateScore();

        assertThat(result).isEqualTo(13);
    }
}
