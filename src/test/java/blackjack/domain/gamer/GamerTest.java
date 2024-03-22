package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamerTest {

    @DisplayName("플레이어의 카드 합이 21이고, 2장의 카드만 가지고 있다면 블랙잭으로 판단한다.")
    @Test
    void blackjackTest() {
        // given
        Gamer gamer = new Player("eden", 0);
        gamer.receiveCard(Card.of(Suit.CLOVER, Rank.ACE));
        gamer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        // when & then
        Assertions.assertThat(gamer.isBlackjack()).isTrue();
    }

    @DisplayName("플레이어의 카드 합이 21이고, 2장 초과의 카드를 가지고 있다면 블랙잭이 아니다.")
    @Test
    void blackjackFailByCardSizeTest() {
        // given
        Gamer gamer = new Player("eden", 0);
        gamer.receiveCard(Card.of(Suit.CLOVER, Rank.ACE));
        gamer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        gamer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        // when & then
        Assertions.assertThat(gamer.isBlackjack()).isFalse();
    }

    @DisplayName("플레이어의 카드 합이 21이 아니면 블랙잭이 아니다.")
    @Test
    void blackjackFailByScoreTest() {
        // given
        Gamer gamer = new Player("eden", 0);
        gamer.receiveCard(Card.of(Suit.CLOVER, Rank.KING));
        gamer.receiveCard(Card.of(Suit.HEART, Rank.KING));

        // when & then
        Assertions.assertThat(gamer.isBlackjack()).isFalse();
    }
}
