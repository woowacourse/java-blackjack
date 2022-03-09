package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.Denomination.*;
import static blackjack.domain.Symbol.*;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("hit 가능하다면 참을 반환한다")
    void canHitWhenTrue() {
        Player player = new Player("pobi", List.of(
                new Card(HEART, KING),
                new Card(CLOVER, QUEEN)
        ));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("hit 가능하지않다면 거짓을 반환한다")
    void canHitWhenFalse() {
        Player player = new Player("pobi", List.of(
                new Card(HEART, KING),
                new Card(CLOVER, QUEEN)
        ));

        player.addCard(new Card(SPADE, ACE));

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 총점이 21 이하인 경우")
    void testWinOrLose() {
        Player player = new Player("pobi",List.of(
                new Card(SPADE, FIVE),
                new Card(DIAMOND, TWO)
        ));
        assertThat(player.isWin(16)).isFalse();
        assertThat(player.isWin(6)).isTrue();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 플레이어의 총점이 21 초과인 경우")
    void testWinOrLose2() {
        Player player = new Player("pobi",List.of(
                new Card(SPADE, KING),
                new Card(SPADE, QUEEN),
                new Card(DIAMOND, TWO)
        ));
        assertThat(player.isWin(1)).isFalse();
    }

    @Test
    @DisplayName("승패 여부를 판단한다 : 딜러의 총점이 21 초과인 경우")
    void testWinOrLose3() {
        Player player = new Player("pobi",List.of(
                new Card(SPADE, ACE),
                new Card(SPADE, ACE)
        ));
        assertThat(player.isWin(22)).isTrue();
    }

    @Test
    @DisplayName("hit이 가능한 상태라면 참을 반환한다.")
    void testIsAbleToHit1() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", initCards);

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙잭이면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit2() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", initCards);
        player.addCard(new Card(DIAMOND, SIX));

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit3() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", initCards);
        player.addCard(new Card(DIAMOND, SEVEN));

        // when
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("stay하면 더이상 hit을 할 수 없다.")
    void testIsAbleToHit4() {
        // given
        List<Card> initCards = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, FIVE));
        Player player = new Player("pobi", initCards);

        // when
        player.stay();
        boolean actual = player.isAbleToHit();

        // then
        assertThat(actual).isFalse();
    }
}
