package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.CardPoint;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static final BetAmount DEFAULT_BET_AMOUNT = new BetAmount(1000);

    @Test
    void 플레이어가_카드를_받는다() {
        Player player = new Player(new Name("모카"), DEFAULT_BET_AMOUNT);

        player.recieveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));

        assertThat(player.getCardCount()).isEqualTo(1);
    }

    @Test
    void 처음_두장의_합이_21이면_블랙잭이다() {
        Player player = new Player(new Name("pobi"), DEFAULT_BET_AMOUNT);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));

        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    void 세장의_합이_21이어도_블랙잭이_아니다() {
        Player player = new Player(new Name("pobi"), DEFAULT_BET_AMOUNT);
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.CLUB));

        assertThat(player.isBlackJack()).isFalse();
    }

    @Test
    void 처음_두장의_합이_21이_아니면_블랙잭이_아니다() {
        Player player = new Player(new Name("pobi"), DEFAULT_BET_AMOUNT);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.NINE, CardPattern.HEART));

        assertThat(player.isBlackJack()).isFalse();
    }
}
