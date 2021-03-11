package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러는 총점수 17이상일시 카드 뽑기를 멈춘다.")
    void testStopDrawDealerWhenTotalScoreOverSeventeen() {
        CardDeck cardDeck = getCustomCardDeck();
        this.dealer.initDraw(cardDeck);

        for (int i = 0; i < 999999; i++) {
            while (!this.dealer.isOverThenLimitScore()) {
                this.dealer.draw(cardDeck.draw());
            }
            assertThat(this.dealer.getScoreToInt()).isEqualTo(17);
        }
    }

    @Test
    @DisplayName("초기 출력을 위한 카드 요청시 첫 1장만 반환한다.")
    void testGetInitFirstOneCard() {
        this.dealer.initDraw(getCustomCardDeck());
        assertThat(this.dealer.getInitCard()).hasSize(1);
        assertThat(this.dealer.getInitCard().get(0).getScore()).isEqualTo(11);
    }

    private CardDeck getCustomCardDeck() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.SIX);
        Card thirdCard = Card.valueOf(Pattern.HEART, Number.TEN);
        Card fourthCard = Card.valueOf(Pattern.HEART, Number.KING);
        return CardDeck.customDeck(Arrays.asList(firstCard, secondCard, thirdCard, fourthCard));
    }
}
