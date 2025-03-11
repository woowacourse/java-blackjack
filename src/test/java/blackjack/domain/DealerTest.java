package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드 덱을 초기화할 수 있다.")
    @Test
    void testDealerInitCardDeck() {
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();

        Dealer dealer = new Dealer(cardDeck, cardDump);
        dealer.initCardDeck();

        assertThat(dealer.getCardDeck().size()).isEqualTo(2);
    }

    @DisplayName("딜러는 카드의 합이 16 이하이면 카드를 한장 더 받아야한다.")
    @Test
    void testDealerGenerate() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        // when
        boolean canTakeExtraCard = dealer.canHit();

        assertThat(canTakeExtraCard).isTrue();
    }

    @DisplayName("딜러는 카드의 합이 17 이상이면 카드를 더 받지 않는다.")
    @Test
    void testDealerGenerate2() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        // when
        boolean canTakeExtraCard = dealer.canHit();

        assertThat(canTakeExtraCard).isFalse();
    }

    @DisplayName("딜러는 자신이 가진 카드 덱의 합을 계산할 수 있다")
    @Test
    void testDealerTotalCardSum() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        // when
        int totalScore = dealer.calculateTotalCardScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("딜러의 점수가 21 초과면 버스트다.")
    @Test
    void testBust_False() {
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        boolean bust = dealer.isBust();

        assertThat(bust).isFalse();
    }

    @DisplayName("딜러의 점수가 21 이하이면 버스트가 아니다.")
    @Test
    void testBust_True() {
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        boolean bust = dealer.isBust();

        assertThat(bust).isTrue();
    }

    @DisplayName("딜러가 여러 에이스를 소유한 경우 버스트 발생 확인")
    @Test
    void testMultipleAce_Bust_True() {
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE));

        Dealer dealer = new Dealer(cardDeck, cardDump);

        boolean bust = dealer.isBust();

        assertThat(bust).isTrue();
    }
}
