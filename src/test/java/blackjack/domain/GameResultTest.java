package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
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
            List<Card> blackjackCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            player.addCards(blackjackCards1.get(0), blackjackCards1.get(1));

            List<Card> blackjackCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            dealer.addCards(blackjackCards2.get(0), blackjackCards2.get(1));

            assertThat(GameResult.getPlayerGameResultFrom(dealer, player)).isEqualTo(GameResult.DRAW);
        }

        @Test
        @DisplayName("버스트 vs 버스트이면 플레이어는 패배를 반환한다.")
        void matchBustVsBust() {
            List<Card> bustCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.THREE)
            );
            player.addCards(bustCards1.get(0), bustCards1.get(1), bustCards1.get(2));

            List<Card> bustCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.NINE),
                    new Card(Suit.CLUB, Denomination.FIVE)
            );
            dealer.addCards(bustCards2.get(0), bustCards2.get(1), bustCards2.get(2));

            assertThat(GameResult.getPlayerGameResultFrom(dealer, player)).isEqualTo(GameResult.LOSE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 승리를 반환한다.")
        void matchNotBlackJackVsBlackJack() {
            List<Card> blackjackCards = List.of(
                    new Card(Suit.SPADE, Denomination.TEN),
                    new Card(Suit.CLUB, Denomination.ACE)
            );
            player.addCards(blackjackCards.get(0), blackjackCards.get(1));

            List<Card> generalCards = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.NINE),
                    new Card(Suit.CLUB, Denomination.TWO)
            );
            dealer.addCards(generalCards.get(0), generalCards.get(1), generalCards.get(2));

            assertThat(GameResult.getPlayerGameResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }

        @Test
        @DisplayName("블랙잭과 버스트가 모두 아니라면 숫자를 비교해 21에 가까우면 이긴다.")
        void matchOthersVsOthers() {
            List<Card> generalCards1 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.CLUB, Denomination.NINE)
            );
            player.addCards(generalCards1.get(0), generalCards1.get(1));

            List<Card> generalCards2 = List.of(
                    new Card(Suit.SPADE, Denomination.KING),
                    new Card(Suit.SPADE, Denomination.FIVE),
                    new Card(Suit.CLUB, Denomination.TWO)
            );
            dealer.addCards(generalCards2.get(0), generalCards2.get(1), generalCards2.get(2));

            assertThat(GameResult.getPlayerGameResultFrom(dealer, player)).isEqualTo(GameResult.WIN);
        }
    }
}
