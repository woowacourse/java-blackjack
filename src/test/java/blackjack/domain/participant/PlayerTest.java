package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerTest {

        @ParameterizedTest
        @ValueSource(strings = {"iiif", "pppk"})
        @DisplayName("한 명의 플레이어를 이름으로 생성할 수 있다.")
        void createPlayerByName(String name) {
            Player player = new Player(new PlayerName(name));

            assertThat(player).isInstanceOf(Player.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"포비_", "sa나!", "훌라627", "HULA,"})
        @DisplayName("이름이 글자가 아니면 예외를 발생시킨다.")
        void createPlayerByEmptyName(String name) {
            assertThatThrownBy(() -> new Player(new PlayerName(name)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 글자만 입력 가능합니다.");
        }
    }

    @Nested
    @DisplayName("플레이어의 추가 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            Player player = new Player(new PlayerName("sana"));

            List<Card> cardsUnder21 = List.of(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            player.addCards(cardsUnder21.get(0), cardsUnder21.get(1), cardsUnder21.get(2));

            assertThat(player.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            Player player = new Player(new PlayerName("sana"));

            List<Card> cardsOver21 = List.of(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );
            player.addCards(cardsOver21.get(0), cardsOver21.get(1), cardsOver21.get(2));

            assertThat(player.isPossibleToAdd()).isFalse();
        }
    }

    @Nested
    @DisplayName("베팅 테스트")
    class betTest {

        @Test
        @DisplayName("베팅 금액을 저장한다")
        void saveBetAmount() {
            Player player = new Player(new PlayerName("hula"));

            assertThatCode(() -> player.bet(1000)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("플레이어가 패배했다면 베팅 금액을 모두 잃는다")
        void lossAllBetAmountWhenPlayerLose() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.SEVEN)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.EIGHT),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(-1000));
        }

        @Test
        @DisplayName("플레이어가 승리했다면 베팅 금액만큼 받는다")
        void betAmountWhenPlayerWin() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.NINE)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.EIGHT),
                    new Card(Suit.SPADE, Denomination.JACK)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(1000));
        }

        @Test
        @DisplayName("플레이어가 버스트라면 베팅 금액을 모두 잃는다")
        void lossAllBetAmount() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.HEART, Denomination.TWO),
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            assertThat(player.isBust()).isTrue();
            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(-1000));
        }

        @Test
        @DisplayName("플레이어가 승리하면 베팅 금액을 돌려받는다")
        void winPlayerBetAmount() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.SEVEN)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(1000));
        }

        @Test
        @DisplayName("무승부일 시. 베팅 금액을 돌려받는다")
        void drawBetAmount() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.JACK)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.QUEEN)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(0));
        }

        @Test
        @DisplayName("플레이어가 블랙잭이라면, 베팅 금액의 1.5배를 받는다")
        void playerBlackjackBetAmount() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.FIVE),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(1500));
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이라면, 베팅 금액만큼만 돌려받는다")
        void playerAndDealerBothBlackjackBetAmount() {
            Player player = new Player(new PlayerName("hula"));
            player.bet(1000);
            player.addCards(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );

            Dealer dealer = new Dealer();
            dealer.addCards(
                    new Card(Suit.CLUB, Denomination.TEN),
                    new Card(Suit.SPADE, Denomination.ACE)
            );

            assertThat(player.calculatePayout(dealer)).isEqualTo(new Payout(1000));
        }
    }
}
