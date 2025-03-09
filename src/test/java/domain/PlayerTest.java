package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    void hit() {
        //given
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));

        Player player = new Player("ad");

        //when
        player.hit(totalCards);
        Cards expected = new Cards();
        expected.add(new Card(Symbol.HEART, Number.FIVE));

        //then
        assertThat(player.getCards()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 총 점수를 구할 수 있다")
    @Test
    void calculatePlayerScore() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FOUR));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when
        int score = player.getScore();
        //then
        assertThat(score).isEqualTo(19);
    }

    @DisplayName("플레이어는 버스트되면 카드를 더 뽑을 수 없다.")
    @Test
    void burstIsNotHit() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();
        totalCards.add(new Card(Symbol.HEART, Number.KING));
        totalCards.add(new Card(Symbol.HEART, Number.JACK));
        totalCards.add(new Card(Symbol.HEART, Number.TWO));

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when //then
        assertThatThrownBy(() -> player.hit(totalCards))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("플레이어는 버스트 되지 않으면 카드를 더 뽑을 수 있다.")
    @Test
    void notBurstHit() {
        //given
        Player player = new Player("ad");
        Cards totalCards = new Cards();

        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));
        totalCards.add(new Card(Symbol.HEART, Number.FIVE));

        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);
        player.hit(totalCards);

        //when //then
        assertThatCode(() -> player.hit(totalCards))
                .doesNotThrowAnyException();
    }

    @DisplayName("게임이 시작되면 플레이어는 2장의 카드를 받는다.")
    @Test
    void startGameGiveDefaultCards() {
        //given
        Player player = new Player("ad");

        Cards cards = new Cards();
        Card card1 = new Card(Symbol.COLVER, Number.ACE);
        Card card2 = new Card(Symbol.COLVER, Number.ACE);
        Card card3 = new Card(Symbol.COLVER, Number.ACE);
        Card card4 = new Card(Symbol.COLVER, Number.ACE);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        //when //then
        assertThatCode(() -> player.prepareGame(cards))
                .doesNotThrowAnyException();
    }

}
