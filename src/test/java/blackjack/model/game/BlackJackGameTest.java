package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Players players;
    private BlackJackGame blackJackGame;
    private Deck deck;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        player1 = new Player("벡터", 1000);
        player2 = new Player("한스", 1000);
        players = new Players(List.of(player1, player2));
        deck = new DeckInitializer().generateDeck();
        blackJackGame = new BlackJackGame(deck);
    }

    @Test
    void 게임이_시작하면_모든_플레이어에게_카드를_두장씩_배분한다() {
        // when
        blackJackGame.initializeGame(players, dealer);

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(2);
        assertThat(player1.getReceivedCards().size()).isEqualTo(2);
        assertThat(player2.getReceivedCards().size()).isEqualTo(2);
    }

    @Test
    void 플레이어가_카드를_한장_추가로_받으면_카드_개수가_증가한다() {
        // given
        blackJackGame.initializeGame(players, dealer);
        int initialSize = player1.getReceivedCards().size();

        // when
        blackJackGame.isBustAfterDraw(player1);

        // then
        assertThat(player1.getReceivedCards().size()).isEqualTo(initialSize + 1);
    }

    @Test
    void 딜러의_점수가_ACE_THRESHOLD_이하면_추가로_카드를_받아야_한다() {
        // given
        blackJackGame.initializeGame(players, dealer);

        // when
        boolean result = blackJackGame.isDealerUnderNumber(dealer);

        // then
        assertThat(result).isEqualTo(dealer.calculatePoint() <= 16);
    }

    @Test
    void 딜러에게_카드를_추가로_지급하면_카드_개수가_증가한다() {
        // given
        blackJackGame.initializeGame(players, dealer);
        int initialSize = dealer.getReceivedCards().size();

        // when
        blackJackGame.giveDealerCard(dealer, deck);

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(initialSize + 1);
    }
}
