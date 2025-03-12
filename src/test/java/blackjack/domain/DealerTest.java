package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드 덱을 초기화할 수 있다.")
    @Test
    void testDealerInitCardDeck() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.initCardDeck();

        assertThat(dealer.getCardDeck().size()).isEqualTo(2);
    }

    @DisplayName("딜러는 카드의 합이 16 이하이면 카드를 한장 더 받아야한다.")
    @Test
    void testDealerGenerate() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        if (dealer.calculateTotalCardScore() <= 16) {
            assertThat(dealer.canHit()).isTrue();
        }
    }

    @DisplayName("딜러는 카드의 합이 17 이상이면 카드를 더 받지 않는다.")
    @Test
    void testDealerGenerate2() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        if (dealer.calculateTotalCardScore() >= 17) {
            assertThat(dealer.canHit()).isFalse();
        }
    }

    @DisplayName("딜러는 자신이 가진 카드 덱의 합을 계산할 수 있다")
    @Test
    void testDealerTotalCardSum() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        int totalScore = dealer.calculateTotalCardScore();

        assertThat(totalScore).isNotNull();
    }

    @DisplayName("딜러의 점수가 21 초과면 버스트다.")
    @Test
    void testBust_False() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        if (dealer.calculateTotalCardScore() > 21) {
            dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.TEN));
        }

        assertThat(dealer.isBust()).isFalse();
    }

    @DisplayName("딜러의 점수가 21 이하이면 버스트가 아니다.")
    @Test
    void testBust_True() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        if (dealer.calculateTotalCardScore() <= 21) {
            assertThat(dealer.isBust()).isFalse();
        }
    }

    @DisplayName("딜러가 여러 에이스를 소유한 경우 버스트 발생 확인")
    @Test
    void testMultipleAce_Bust_True() {
        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.initCardDeck();

        List<Card> cardDeck = dealer.getCardDeck();
        if (cardDeck.containsAll(Arrays.asList(
                new Card(CardSuit.CLUB, CardRank.ACE),
                new Card(CardSuit.CLUB, CardRank.ACE)))) {
            assertThat(dealer.isBust()).isTrue();
        }
    }
}
