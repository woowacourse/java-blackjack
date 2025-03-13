package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.game.GameResult;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.user.Wallet;
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
            Player player = new Player(new PlayerName(name), Wallet.initialBetting(10000));

            assertThat(player).isInstanceOf(Player.class);
        }
    }

    @Nested
    @DisplayName("플레이어의 추가 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 3);

            assertThat(player.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 3);

            assertThat(player.isPossibleToAdd()).isFalse();
        }
    }

    @Nested
    @DisplayName("플레이어 수익 테스트")
    class WalletTest {

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void updateWalletWithBlackjack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("if"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 2);

            int result = player.updateWalletByGameResult(GameResult.WIN);
            assertThat(result).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void updateWalletWithJustWin() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 3);

            int result = player.updateWalletByGameResult(GameResult.WIN);
            assertThat(result).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void updateWalletWithDraw() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 2);

            int result = player.updateWalletByGameResult(GameResult.DRAW);
            assertThat(result).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void updateWalletWithLose() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TWO),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), Wallet.initialBetting(10000));
            player.addCards(cardDeck, 2);

            int result = player.updateWalletByGameResult(GameResult.LOSE);
            assertThat(result).isEqualTo(-10000);
        }
    }
}
