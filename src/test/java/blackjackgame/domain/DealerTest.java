package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CardValue;
import blackjackgame.domain.card.Symbol;
import blackjackgame.domain.player.Dealer;

class DealerTest {
    @DisplayName("딜러가 가진 카드들의 합이 16이하면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreUnder16() {
        Card five = new Card(Symbol.SPADE, CardValue.FIVE);
        Card eight = new Card(Symbol.CLOVER, CardValue.EIGHT);
        Dealer dealer = new Dealer(List.of(five, eight));

        assertThat(dealer.canHit()).isEqualTo(true);
    }

    @DisplayName("딜러가 가진 카드들의 합이 17이상이면, true를 반환한다.")
    @Test
    void Should_PickOneCard_When_ScoreOver17() {
        Card jack = new Card(Symbol.SPADE, CardValue.JACK);
        Card king = new Card(Symbol.CLOVER, CardValue.KING);
        Dealer dealer = new Dealer(List.of(jack, king));

        assertThat(dealer.canHit()).isEqualTo(false);
    }
}
