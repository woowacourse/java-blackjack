package domain.result;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.gamer.*;
import domain.result.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackRuleTest {
    @Test
    void 점수_계산_테스트() {
        BlackJackRule blackJackRule = new BlackJackRule();

        Name name = new Name("phobi");
        Hand hand = new Hand();

        hand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(11));

        hand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(21));

        hand.drawCard(() -> Card.of(Rank.THREE, Suit.CLOVER));
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(Score.of(14));
    }

    @Test
    void 플레이어_결과_계산_테스트() {
        BlackJackRule blackJackRule = new BlackJackRule();

        Hand playerHand = new Hand();
        playerHand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        playerHand.drawCard(() -> Card.of(Rank.ACE, Suit.CLOVER));

        Hand dealerHand = new Hand();
        dealerHand.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        dealerHand.drawCard(() -> Card.of(Rank.KING, Suit.CLOVER));

        Player player = new Player(new Name("phobi"), playerHand, new Money(1000));
        Dealer dealer = new Dealer(dealerHand);

        assertThat(blackJackRule.derivePlayerResult(player, dealer).getBettingMoney()).isEqualTo(new Money(1500));
    }
}
