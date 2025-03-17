package blackjack.user.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.user.dealer.Dealer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerTest {

        @ParameterizedTest
        @ValueSource(strings = {"iiif", "pppk"})
        @DisplayName("한 명의 플레이어를 이름으로 생성할 수 있다.")
        void createPlayerByName(String name) {
            Player player = createPlayerWithNoMoney(name);

            assertThat(player).isInstanceOf(Player.class);
        }
    }

    @Nested
    @DisplayName("플레이어의 카드 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            Player player = createPlayerWithNoMoney("sana");
            player.addCards(dealer.pickCards(3));

            assertThat(player.getCardHand().openCards()).hasSize(3);
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.CLUB, Denomination.THREE)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            Player player = createPlayerWithNoMoney("sana");
            player.addCards(dealer.pickCards(3));

            assertThatThrownBy(() -> player.addCards(dealer.pickCards(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("배팅 금액 테스트")
    class BetAmountTest {

        @Test
        @DisplayName("새로운 배팅 금액으로 변경할 수 있다.")
        void updateAmount() {
            BetAmount beforeAmount = BetAmount.initAmount();
            Player player = Player.createPlayer(new PlayerName("sana"), beforeAmount);

            BetAmount afterAmount = BetAmount.initAmountWithPrincipal(10000);
            player.updateAmount(afterAmount);

            assertThat(player.getBetAmount()).isNotSameAs(beforeAmount);
        }
    }

    @Nested
    @DisplayName("카드 오픈 테스트")
    class OpenCardTest {

        @Test
        @DisplayName("플레이어는 초기 카드를 두 장 오픈할 수 있다.")
        void openFirstCard() {
            List<Card> cards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING)
            ));
            CardDeck cardDeck = new CardDeck(cards);
            Dealer dealer = Dealer.createDealer(cardDeck);

            Player player = createPlayerWithNoMoney("sana");
            player.addCards(dealer.pickCards(2));

            List<Card> result = player.openInitialCards();

            assertAll(() -> {
                assertThat(result).hasSize(2);
                assertThat(result.getFirst()).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
                assertThat(result.getLast()).isEqualTo(new Card(Suit.SPADE, Denomination.KING));
            });
        }
    }

    private Player createPlayerWithNoMoney(String name) {
        return Player.createPlayer(new PlayerName(name), BetAmount.initAmount());
    }
}
