package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.Hands;
import blackjack.domain.deck.Rank;
import blackjack.domain.deck.Shape;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
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
    @DisplayName("플레이어가 카드를 잘 받는다.")
    void playerReceiveCardTest() {
        Player player = new Player(new Name("이름"));
        Assertions.assertThatNoException()
                .isThrownBy(() -> player.receiveCard(new Card(Shape.HEART, Rank.ACE)));
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

    @Test
    @DisplayName("플레이어 카드가 21이 넘지 않는다.")
    void isNotOverTrueTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.ACE));
        player.receiveCard(new Card(Shape.HEART, Rank.NINE));

        assertThat(player.isNotOver(21)).isTrue();
    }

    @Test
    @DisplayName("플레이어 카드가 21이 넘는다.")
    void isNotOverFalseTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.NINE));
        player.receiveCard(new Card(Shape.SPADE, Rank.NINE));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.FOUR));

        assertThat(player.isNotOver(21)).isFalse();
    }
}
