package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    private BlackJackGame blackJackGame;
    private Deck deck;

    @BeforeEach
    void setUp() {
        blackJackGame = initBlackJackGame();
        deck = Deck.init();
    }

    @Test
    @DisplayName("BlackJackGame의 Player에게 최초 카드 두장을 준다.")
    void initializeBlackJackGame() {
        blackJackGame.giveFirstCards(deck);

        assertThat(deck.size()).isEqualTo(46);
    }

    private BlackJackGame initBlackJackGame() {
        Player dealer = new Dealer();
        List<Player> gamers = List.of(new Gamer("judy"), new Gamer("huni"));
        return new BlackJackGame(dealer, gamers);
    }
}
