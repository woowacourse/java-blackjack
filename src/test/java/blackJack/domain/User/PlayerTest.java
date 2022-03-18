package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Denomination;
import blackJack.domain.Card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player = new Player("test", 10000);

    @Test
    @DisplayName("플레이어가 블랙잭인지 확인한다.")
    void checkPlayerIsBlackJackTest() {
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        player.cards.add(new Card(Suit.HEART, Denomination.ACE));
        assertThat(player.isBlackJack()).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어가 소지한 카드가 21 미만이면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        assertThat(player.isPossibleToAdd()).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어가 소지한 카드가 21 이상이면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        player.cards.add(new Card(Suit.HEART, Denomination.JACK));
        player.cards.add(new Card(Suit.HEART, Denomination.TEN));
        player.cards.add(new Card(Suit.HEART, Denomination.TEN));
        assertThat(player.isPossibleToAdd()).isEqualTo(false);
    }

}