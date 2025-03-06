package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
class GameResultTest {

    @Nested
    @DisplayName("승패 결정 테스트")
    class MatchResultTest {

        @Test
        @DisplayName("블랙잭 vs 블랙잭이면 무승부를 반환한다.")
        void matchBlackJackVsBlackJack() {
            Player player = new Player("sana");
            Card card1_player = new Card(Suit.SPADE, Denomination.TEN);
            Card card2_player = new Card(Suit.CLUB, Denomination.ACE);
            player.addCards(card1_player, card2_player);

            Dealer dealer = new Dealer();
            Card card1_dealer = new Card(Suit.SPADE, Denomination.KING);
            Card card2_dealer = new Card(Suit.CLUB, Denomination.ACE);
            dealer.addCards(card1_dealer, card2_dealer);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("버스트 vs 버스트이면 플레이어는 패배를 반환한다.")
        void matchBustVsBust() {
            Player player = new Player("sana");
            Card card1_player = new Card(Suit.SPADE, Denomination.TEN);
            Card card2_player = new Card(Suit.CLUB, Denomination.KING);
            Card card3_player = new Card(Suit.CLUB, Denomination.THREE);
            player.addCards(card1_player, card2_player, card3_player);

            Dealer dealer = new Dealer();
            Card card1_dealer = new Card(Suit.SPADE, Denomination.KING);
            Card card2_dealer = new Card(Suit.SPADE, Denomination.NINE);
            Card card3_dealer = new Card(Suit.CLUB, Denomination.FIVE);
            dealer.addCards(card1_dealer, card2_dealer, card3_dealer);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 승리를 반환한다.")
        void matchNotBlackJackVsBlackJack() {
            Player player = new Player("sana");
            Card card1_player = new Card(Suit.SPADE, Denomination.KING);
            Card card2_player = new Card(Suit.CLUB, Denomination.ACE);
            player.addCards(card1_player, card2_player);

            Dealer dealer = new Dealer();
            Card card1_dealer = new Card(Suit.SPADE, Denomination.KING);
            Card card2_dealer = new Card(Suit.SPADE, Denomination.NINE);
            Card card3_dealer = new Card(Suit.CLUB, Denomination.TWO);
            dealer.addCards(card1_dealer, card2_dealer, card3_dealer);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("블랙잭과 버스트가 모두 아니라면 숫자를 비교해 21에 가까우면 이긴다.")
        void matchOthersVsOthers() {
            Player player = new Player("sana");
            Card card1_player = new Card(Suit.SPADE, Denomination.KING);
            Card card2_player = new Card(Suit.CLUB, Denomination.NINE);
            player.addCards(card1_player, card2_player);

            Dealer dealer = new Dealer();
            Card card1_dealer = new Card(Suit.SPADE, Denomination.KING);
            Card card2_dealer = new Card(Suit.SPADE, Denomination.FIVE);
            Card card3_dealer = new Card(Suit.CLUB, Denomination.TWO);
            dealer.addCards(card1_dealer, card2_dealer, card3_dealer);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }
    }
}