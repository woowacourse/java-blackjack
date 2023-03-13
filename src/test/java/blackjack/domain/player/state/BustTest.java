package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.result.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BustTest {
    @Test
    @DisplayName("bust 시 수익 확인")
    void bust() {
        Hit hit = new Hit(Card.of(Suit.DIAMOND, Letter.KING), Card.of(Suit.DIAMOND, Letter.JACK));
        var bust = hit.draw(Card.of(Suit.DIAMOND, Letter.JACK));
        double profit = bust.calculateProfit(false,
                new Score(10), new Score(30), new BettingMoney("3000"));
        assertThat(profit).isEqualTo(-3000);
    }
}
