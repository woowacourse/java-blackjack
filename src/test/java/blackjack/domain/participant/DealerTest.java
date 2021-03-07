package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.GameResult;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    Dealer dealer;
    CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    @Test
    @DisplayName("딜러는 총점수 17이상일시 카드 뽑기를 멈춘다.")
    void testStopDrawDealerWhenTotalScoreOverSeventeen() {
        for (int i = 0; i < 999999; i++) {
            while (this.dealer.isHitable()) {
                this.dealer.addCard(this.cardDeck.draw());
            }
            assertThat(this.dealer.getScore()).isGreaterThanOrEqualTo(17);
        }
    }

    @Test
    @DisplayName("플레이어 승패 테스트")
    void testPlayerWinOrLose() {
        Player player = new Player(new Name("미립"));

        this.dealer.addCard(Card.valueOf(Pattern.HEART, Number.FIVE));  // 5
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.THREE));    // 3
        assertThat(this.dealer.judge(player)).isEqualTo(GameResult.LOSE);

        this.dealer.addCard(Card.valueOf(Pattern.HEART, Number.THREE)); // 8
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.FIVE));     // 8
        assertThat(this.dealer.judge(player)).isEqualTo(GameResult.DRAW);

        this.dealer.addCard(Card.valueOf(Pattern.HEART, Number.SIX));   // 14
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.SEVEN));    // 15
        assertThat(this.dealer.judge(player)).isEqualTo(GameResult.WIN);

        this.dealer.addCard(Card.valueOf(Pattern.HEART, Number.KING));  // Burst
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.KING));     // Burst
        assertThat(this.dealer.judge(player)).isEqualTo(GameResult.LOSE);
    }
}
