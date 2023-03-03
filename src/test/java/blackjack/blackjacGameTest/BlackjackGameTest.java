package blackjack.blackjacGameTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import blackjackGame.BlackjackGame;
import card.Card;
import card.CardNumber;
import card.Pattern;
import deck.CardsGenerator;
import deck.Deck;
import player.Dealer;
import player.Name;
import player.Player;
import player.Players;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Players players;
    Dealer dealer;
    Deck deck;

    @BeforeEach
    void setUp() {

        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator();
        deck = new Deck(fixedCardsGenerator);
        players = new Players();
        dealer = new Dealer();
        blackjackGame = new BlackjackGame(players, dealer, deck);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {
        blackjackGame.addPlayer(new Player(new Name("폴로")));
        blackjackGame.addPlayer(new Player(new Name("로지")));

        assertThat(players.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 카드를 두 장 줄 수 있다.")
    void supplyCardsToDealer() {
        blackjackGame.supplyCardsToDealer();

        assertThat(dealer.showCards())
                .contains(new Card(CardNumber.ACE, Pattern.DIAMOND), new Card(CardNumber.ACE, Pattern.SPADE));
    }

    @Test
    @DisplayName("플레이어들에게 카드를 두 장씩 줄 수 있다.")
    void supplyCardsToPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        blackjackGame.supplyCardsToPlayers();

        assertThat(player1.showCards()).contains(new Card(CardNumber.ACE, Pattern.DIAMOND),
                new Card(CardNumber.ACE, Pattern.SPADE));
        assertThat(player2.showCards()).contains(new Card(CardNumber.ACE, Pattern.CLOVER),
                new Card(CardNumber.ACE, Pattern.HEART));

    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어는 카드를 한장 추가로 받을 수 있다.")
    void supplyAdditionalCard() {
        Player player1 = new Player(new Name("폴로"));
        blackjackGame.addPlayer(player1);
        blackjackGame.supplyCardsToPlayers();

        blackjackGame.supplyAdditionalCard(0);

        assertThat(player1.showCards()).contains(new Card(CardNumber.ACE, Pattern.DIAMOND),
                new Card(CardNumber.ACE, Pattern.SPADE), new Card(CardNumber.ACE, Pattern.CLOVER));
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어의 버스트를 확인할 수 있다.")
    void isBust() {
        CardsGenerator fixedGenerator = () -> {
            Stack<Card> cards = new Stack<>();
            cards.push(new Card(CardNumber.KING, Pattern.HEART));
            cards.push(new Card(CardNumber.KING, Pattern.CLOVER));
            cards.push(new Card(CardNumber.KING, Pattern.DIAMOND));
            cards.push(new Card(CardNumber.KING, Pattern.SPADE));
            return cards;
        };

        Deck fixedDeck = new Deck(fixedGenerator);
        blackjackGame = new BlackjackGame(players, dealer, fixedDeck);

        Player player1 = new Player(new Name("폴로"));
        blackjackGame.addPlayer(player1);
        blackjackGame.supplyCardsToPlayers();
        blackjackGame.supplyAdditionalCard(0);

        assertThat(blackjackGame.isBust(0)).isTrue();
    }


    @Test
    @DisplayName("현재 플레이어의 인원수를 반환할 수 있다.")
    void countPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        assertThat(blackjackGame.countPlayer()).isEqualTo(2);
    }

    @Nested
    @DisplayName("딜러가 카드를 추가로 받을 수 있는지 확인하는 기능")
    class canDealerHitTest {
        @Test
        @DisplayName("딜러가 버스트가 아니고 언더스코어인 경우")
        void canDealerHitUnderScoreAndNotBust() {
            dealer.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.TWO, Pattern.SPADE));

            assertThat(blackjackGame.canDealerHit()).isTrue();
        }

        @Test
        @DisplayName("딜러가 버스트인경우")
        void cantDealerHitCuzBust() {
            dealer.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.KING, Pattern.SPADE));
            dealer.hit(new Card(CardNumber.TWO, Pattern.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }

        @Test
        @DisplayName("버스트가 아니고, 언더스코어가 아닌경우")
        void canDealerHit() {
            dealer.hit(new Card(CardNumber.KING, Pattern.HEART));
            dealer.hit(new Card(CardNumber.EIGHT, Pattern.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }
    }
}
