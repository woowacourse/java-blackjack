package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    //TODO : 생성 부분 리팩터링
    @DisplayName("플레이어가 카드를 뽑아서 저장한다.")
    @Test
    void hitTest() {
        // given
        Card card = new Card(Symbol.HEART, Rank.NINE);

        Name name = new Name("lini");
        Player player = new Player(name);

        // when
        player.hit(card);

        // then
        assertThat(player.getHand()).hasSize(1);
    }

    @DisplayName("플레이어가 가진 카드의 점수를 알 수 있다.")
    @Test
    void calculateTotalScoreTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);

        Name name = new Name("lini");
        Player player = new Player(name);

        player.hit(card1);
        player.hit(card2);
        int expectedScore = 19;

        // when
        int result = player.calculateTotalScore();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("플레이어가 버스트인지 확인한다.")
    @Test
    void isBust() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        Name name = new Name("lini");
        Player player = new Player(name);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        // when
        boolean bust = player.isBust();

        // then
        assertThat(bust).isTrue();
    }

    @DisplayName("플레이어가 카드를 더 받을 수 있는지 판단한다.")
    @Test
    void isStayTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        Name name = new Name("lini");
        Player player = new Player(name);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        // when
        boolean stay = player.isStay();

        // then
        assertThat(stay).isTrue();
    }
}
