package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.KING;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.TEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게이머")
public class GamerTest {
    @Test
    @DisplayName("처음 받은 카드 두장을 반환한다.")
    void gameDealTest() {
        // given
        Gamer gamer = new Gamer(new Hand(), new Chip(0L));

        // when
        gamer.draw(List.of(Card.of(ACE, CLUB), Card.of(NINE, CLUB)));

        // then
        assertThat(gamer.cards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void gameHitTest() {
        // given
        Gamer gamer = new Gamer(new Hand(), new Chip(0L));

        // when
        gamer.draw(Card.of(KING, DIAMOND));

        // then
        assertThat(gamer.cards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 점수의 합이 21이고 카드가 2장일 경우 블랙잭이다.")
    void blackjackTest() {
        // given
        Gamer gamer = new Gamer(new Hand(), new Chip(0L));

        // when
        gamer.draw(List.of(Card.of(TEN, DIAMOND), Card.of(ACE, CLUB)));

        // then
        assertThat(gamer.isBlackjack()).isEqualTo(true);
    }
}
