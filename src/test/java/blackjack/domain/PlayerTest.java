package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Nested
public class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerTest {

        @Test
        @DisplayName("한 명의 플레이어를 이름으로 생성할 수 있다.")
        void createPlayerByName() {
            String name = "jason";
            Player player = new Player(name);

            assertThat(player).isInstanceOf(Player.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"포비", "sa나", "훌라627", "HULA"})
        @DisplayName("영어 이름이 아니면 예외를 발생시킨다.")
        void createPlayerByEmptyName(String name) {
            assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 알파벳 소문자만 입력 가능합니다.");
        }
    }

    @Nested
    @DisplayName("플레이어의 추가 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.ACE);

            player.addCards(card1, card2, card3);

            assertThat(player.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.JACK);

            player.addCards(card1, card2, card3);

            assertThat(player.isPossibleToAdd()).isFalse();
        }
    }

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

            assertThat(player.matchGame(dealer)).isEqualTo(GameResult.DRAW);
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

            assertThat(player.matchGame(dealer)).isEqualTo(GameResult.LOSE);
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

            assertThat(player.matchGame(dealer)).isEqualTo(GameResult.WIN);
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

            assertThat(player.matchGame(dealer)).isEqualTo(GameResult.WIN);
        }
    }
}
