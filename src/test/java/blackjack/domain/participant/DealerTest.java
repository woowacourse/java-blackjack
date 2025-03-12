package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.GameResult;
import blackjack.domain.TestUtil;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("카드가 16이하면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SIX));

        Dealer dealer = new Dealer(cardHand);

        // when
        boolean canHit = dealer.canHit();

        // then
        assertThat(canHit).isTrue();
    }

    @DisplayName("카드가 16이 초과한다면 카드를 더 뽑을 수 없다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardHand);

        // when
        boolean canHit = dealer.canHit();

        // then
        assertThat(canHit).isFalse();
    }

    @DisplayName("딜러는 받은 카드 중 1장의 카드만을 보여준다.")
    @Test
    void test_showStartCards() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer score = new Dealer(cardHand);

        // when
        List<Card> startCards = score.showStartCards();

        // then
        assertThat(startCards).hasSize(1);
    }
    
    @Nested
    @DisplayName("플레이어를 자신과 비교해 결과를 알려준다. 딜러는 플레이어 결과의 반대이다.")
    class informResultToPlayerTest {
        @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 딜러가 이긴다.")
        @Test
        void testWinnerEvaluation_dealerWin() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 플레이어가 이긴다.")
        @Test
        void testWinnerEvaluation_dealerLose() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.FIVE));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.LOSE);
        }

        @DisplayName("딜러와 플레이어 점수가 같으면 무승부다.")
        @Test
        void testWinnerEvaluation_draw() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.DRAW);
            assertThat(playerResult).isEqualTo(GameResult.DRAW);
        }

        @DisplayName("플레이어가 버스트인 경우, 딜러가 이기고 플레이어가 패배한다.")
        @Test
        void testWinnerEvaluation_playerBusted() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.WIN);
            assertThat(playerResult).isEqualTo(GameResult.LOSE);

        }

        @DisplayName("딜러가 버스트인 경우, 딜러가 지고 플레이어가 승리한다")
        @Test
        void testWinnerEvaluation_dealerBusted() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Dealer dealer = new Dealer(cardHand2);

            //when
            
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.LOSE);
            assertThat(playerResult).isEqualTo(GameResult.WIN);
        }

        @DisplayName("플레이어가 블랙잭인 경우, 같은 21이어도 플레이어가 블랙잭으로 이긴다.")
        @Test
        void testWinnerEvaluation_playerBlackjack() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.FIVE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.SIX));

            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);
            
            //then
            assertThat(dealerResult).isEqualTo(GameResult.LOSE);
            assertThat(playerResult).isEqualTo(GameResult.BLACKJACK_WIN);
        }

        @DisplayName("플레이어와 딜러 둘다 블랙잭인 경우 무승부다.")
        @Test
        void testWinnerEvaluation_BlackjackDraw() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.ACE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.DRAW);
            assertThat(playerResult).isEqualTo(GameResult.DRAW);
        }

        @DisplayName("딜러가 블랙잭이고 플레이어가 일반 21인 경우 딜러가 이긴다.")
        @Test
        void testWinnerEvaluation_dealerBlackjack() {
            //given
            CardHand cardHand1 = new CardHand();
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.FIVE));
            cardHand1.add(new Card(CardSuit.CLUB, CardRank.SIX));

            Player player = TestUtil.createPlayerOf("player", cardHand1);

            CardHand cardHand2 = new CardHand();
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.ACE));
            cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

            Dealer dealer = new Dealer(cardHand2);

            //when
            GameResult playerResult = dealer.informResultTo(player);
            GameResult dealerResult = GameResult.reverse(playerResult);

            //then
            assertThat(dealerResult).isEqualTo(GameResult.WIN);
            assertThat(playerResult).isEqualTo(GameResult.LOSE);
        }
    }

}
