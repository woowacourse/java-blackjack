package blackjack.domain.gamer;

import blackjack.domain.supplies.Card;
import blackjack.domain.supplies.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.supplies.Rank.ACE;
import static blackjack.domain.supplies.Rank.KING;
import static blackjack.domain.supplies.Rank.NINE;
import static blackjack.domain.supplies.Rank.TEN;
import static blackjack.domain.supplies.Suit.CLUB;
import static blackjack.domain.supplies.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어")
public class PlayerTest {

    @Test
    @DisplayName("처음 받은 카드 두장을 반환한다.")
    void gameDealTest() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(0));

        // when
        player.draw(List.of(new Card(ACE, CLUB), new Card(NINE, CLUB)));

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void gameHitTest() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(0));

        // when
        player.draw(new Card(KING, DIAMOND));

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 점수의 합이 21이고 카드가 2장일 경우 블랙잭이다.")
    void blackjackTest() {
        // given
        Player player = new Player(new Name("lemone"), new Chip(0));

        // when
        player.draw(List.of(new Card(TEN, DIAMOND), new Card(ACE, CLUB)));

        // then
        assertThat(player.isBlackjack()).isEqualTo(true);
    }
}
