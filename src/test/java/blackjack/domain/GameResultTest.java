package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerName;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Nested
    @DisplayName("승패 결정 테스트")
    class MatchResultTest {

        Player player;
        Dealer dealer;

        @BeforeEach
        void initParticipants() {
            player = new Player(new PlayerName("sana"));
            dealer = new Dealer();
        }

        @Test
        @DisplayName("블랙잭 vs 블랙잭이면 무승부를 반환한다.")
        void matchBlackJackVsBlackJack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 2);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("버스트 vs 버스트이면 플레이어는 패배를 반환한다.")
        void matchBustVsBust() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.KING),
                new Card(Suit.CLUB, Denomination.THREE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.NINE),
                new Card(Suit.CLUB, Denomination.FIVE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 3);
            dealer.addCards(cardDeck, 3);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 승리를 반환한다.")
        void matchNotBlackJackVsBlackJack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.NINE),
                new Card(Suit.CLUB, Denomination.TWO)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 3);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("블랙잭과 버스트가 모두 아니라면 숫자를 비교해 21에 가까우면 이긴다.")
        void matchOthersVsOthers() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.CLUB, Denomination.NINE),
                new Card(Suit.SPADE, Denomination.KING),
                new Card(Suit.SPADE, Denomination.FIVE),
                new Card(Suit.CLUB, Denomination.TWO)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            player.addCards(cardDeck, 2);
            dealer.addCards(cardDeck, 3);

            assertThat(GameResult.playerResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }
    }
}
