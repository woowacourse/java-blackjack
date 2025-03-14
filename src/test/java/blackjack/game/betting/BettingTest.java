package blackjack.game.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Nested
    @DisplayName("배팅 생성 테스트")
    class CreateBettingTest {

        @Test
        @DisplayName("배팅금 입력 전에는 빈 테이블은 생성한다.")
        void createEmptyTable() {
            Betting betting = Betting.createWithEmptyTable();
            assertThat(betting.getBettingTable()).hasSize(0);
        }
    }

    @Nested
    @DisplayName("플레이어 수익 테스트")
    class BetAmountTest {

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void updateWalletWithBlackjack() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.THREE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("if"), new CardHand(21));
            player.addCards(cardDeck, 2);
            Map<PlayerName, BetAmount> bettingTable = new LinkedHashMap<>(Map.of(player.getName(),
                BetAmount.initialBetting(10000)));

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            Betting betting = new Betting(bettingTable);
            betting.calculateProfitForPlayer(dealer, List.of(player));
            Map<PlayerName, BetAmount> updateBettingTable = betting.getBettingTable();

            assertThat(updateBettingTable.get(player.getName()).getProfit()).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void updateWalletWithJustWin() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.FIVE),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.THREE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), new CardHand(21));
            player.addCards(cardDeck, 3);
            Map<PlayerName, BetAmount> bettingTable = new LinkedHashMap<>(Map.of(player.getName(),
                BetAmount.initialBetting(10000)));

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            Betting betting = new Betting(bettingTable);
            betting.calculateProfitForPlayer(dealer, List.of(player));
            Map<PlayerName, BetAmount> updateBettingTable = betting.getBettingTable();

            assertThat(updateBettingTable.get(player.getName()).getProfit()).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void updateWalletWithDraw() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.SIX),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), new CardHand(21));
            player.addCards(cardDeck, 2);
            Map<PlayerName, BetAmount> bettingTable = new LinkedHashMap<>(Map.of(player.getName(),
                BetAmount.initialBetting(10000)));

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            Betting betting = new Betting(bettingTable);
            betting.calculateProfitForPlayer(dealer, List.of(player));
            Map<PlayerName, BetAmount> updateBettingTable = betting.getBettingTable();

            assertThat(updateBettingTable.get(player.getName()).getProfit()).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void updateWalletWithLose() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.SPADE, Denomination.TWO),
                new Card(Suit.CLUB, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), new CardHand(31));
            player.addCards(cardDeck, 2);
            Map<PlayerName, BetAmount> bettingTable = new LinkedHashMap<>(Map.of(player.getName(),
                BetAmount.initialBetting(10000)));

            Dealer dealer = new Dealer(new CardHand(17));
            dealer.addCards(cardDeck, 2);

            Betting betting = new Betting(bettingTable);
            betting.calculateProfitForPlayer(dealer, List.of(player));
            Map<PlayerName, BetAmount> updateBettingTable = betting.getBettingTable();

            assertThat(updateBettingTable.get(player.getName()).getProfit()).isEqualTo(-10000);
        }
    }
}
