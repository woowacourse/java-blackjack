package blackjack.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.game.GameResult;
import blackjack.game.betting.BetAmount;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BetPlayerTest {

    @Nested
    @DisplayName("플레이어 수익 테스트")
    class BetAmountTest {

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void updateWalletWithBlackjack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            BettingPlayer bettingPlayer = new BettingPlayer(new PlayerName("if"), new CardHand(),
                BetAmount.initialBetting(10000));
            bettingPlayer.addCards(cardDeck, 2);

            int result = bettingPlayer.updateWalletByGameResult(GameResult.WIN);
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

            BettingPlayer bettingPlayer = new BettingPlayer(new PlayerName("sana"), new CardHand(),
                BetAmount.initialBetting(10000));
            bettingPlayer.addCards(cardDeck, 3);

            int result = bettingPlayer.updateWalletByGameResult(GameResult.WIN);
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

            BettingPlayer bettingPlayer = new BettingPlayer(new PlayerName("sana"), new CardHand(),
                BetAmount.initialBetting(10000));
            bettingPlayer.addCards(cardDeck, 2);

            int result = bettingPlayer.updateWalletByGameResult(GameResult.DRAW);
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

            BettingPlayer bettingPlayer = new BettingPlayer(new PlayerName("sana"), new CardHand(),
                BetAmount.initialBetting(10000));
            bettingPlayer.addCards(cardDeck, 2);

            int result = bettingPlayer.updateWalletByGameResult(GameResult.LOSE);
            assertThat(result).isEqualTo(-10000);
        }
    }
}
