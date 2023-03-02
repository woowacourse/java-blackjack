import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;
import deck.CardsGenerator;
import deck.Deck;
import fixedCaradsGenerator.FixedCardsGenerator;
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
}
