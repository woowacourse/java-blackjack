package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PlayerTest {

    Player player;

    @BeforeEach
    void setting() {
        player = new Player(new Name("test"), new ArrayList<>());
        player.draw(new Card(Shape.DIAMOND, Letter.TEN));
        player.draw(new Card(Shape.DIAMOND, Letter.NINE));
    }

    @Test
    @DisplayName("플레이어의 점수가 21 이하일 때, 히트인지 테스트")
    void isHitTest() {
        // when
        player.draw(new Card(Shape.DIAMOND, Letter.TWO));

        // then
        Assertions.assertThat(player.isHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21 이상일 때, 히트인지 테스트")
    void isNotHitTest() {
        // when
        player.draw(new Card(Shape.DIAMOND, Letter.THREE));

        // then
        Assertions.assertThat(player.isHit()).isFalse();
    }
}
