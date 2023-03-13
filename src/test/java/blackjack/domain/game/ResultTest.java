package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private static final Card SPADE_ACE = Card.of(Suit.SPADE, Denomination.ACE);
    private static final Card SPADE_TEN = Card.of(Suit.SPADE, Denomination.TEN);
    private static final Card CLOVER_ACE = Card.of(Suit.CLOVER, Denomination.ACE);
    private static final Card CLOVER_JACK = Card.of(Suit.CLOVER, Denomination.TEN);
    private static final Card DIAMOND_FIVE = Card.of(Suit.DIAMOND, Denomination.FIVE);
    private static final Card DIAMOND_SIX = Card.of(Suit.DIAMOND, Denomination.SIX);

    @Test
    @DisplayName("플레이어가 첫 두장이 21이면 블랙잭이다.")
    void blackJack() {
        Hand playerHand = new Hand();
        playerHand.add(SPADE_ACE);
        playerHand.add(SPADE_TEN);
        Hand dealerHand = new Hand();
        dealerHand.add(DIAMOND_SIX);
        dealerHand.add(CLOVER_JACK);

        assertThat(Result.calculateResult(playerHand, dealerHand)).isEqualTo(Result.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어가 딜러보다 카드가 높으면 이긴다")
    void win() {
        Hand playerHand = new Hand();
        playerHand.add(SPADE_ACE);
        playerHand.add(DIAMOND_SIX);
        Hand dealerHand = new Hand();
        dealerHand.add(SPADE_TEN);
        dealerHand.add(DIAMOND_FIVE);

        assertThat(Result.calculateResult(playerHand, dealerHand)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어가 딜러보다 카드가 낮으면 진다")
    void lose() {
        Hand playerHand = new Hand();
        playerHand.add(SPADE_TEN);
        playerHand.add(DIAMOND_FIVE);
        Hand dealerHand = new Hand();
        dealerHand.add(SPADE_ACE);
        dealerHand.add(DIAMOND_SIX);

        assertThat(Result.calculateResult(playerHand, dealerHand)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 딜러와 동점이면 비긴다.")
    void draw() {
        Hand playerHand = new Hand();
        playerHand.add(SPADE_ACE);
        playerHand.add(SPADE_TEN);
        Hand dealerHand = new Hand();
        dealerHand.add(CLOVER_ACE);
        dealerHand.add(CLOVER_JACK);

        assertThat(Result.calculateResult(playerHand, dealerHand)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("플레이어가 버건트면 무조건 진다.")
    void bust() {
        Hand playerHand = new Hand();
        playerHand.add(DIAMOND_SIX);
        playerHand.add(SPADE_TEN);
        playerHand.add(CLOVER_JACK);
        Hand dealerHand = new Hand();
        dealerHand.add(DIAMOND_SIX);
        dealerHand.add(SPADE_TEN);
        dealerHand.add(CLOVER_JACK);

        assertThat(Result.calculateResult(playerHand, dealerHand)).isEqualTo(Result.LOSE);
    }
}
