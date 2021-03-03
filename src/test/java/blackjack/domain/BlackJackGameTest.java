package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.controller.BlackJackController;
import blackjack.domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        blackJackGame = new BlackJackGame("a,b,c", Card.getAllCards());
    }

    @DisplayName("초기 덱 생성 검증")
    @Test
    void checkInitialDeck() {
        assertThat(blackJackGame.getDeck().getCards().size()).isEqualTo(52);
    }

    @DisplayName("초기 카드 분배 검증")
    @Test
    void checkInitialCardDistribution() {
        blackJackGame.prepare();
        blackJackGame.getPlayers().stream()
            .forEach(player -> assertThat(player.getHand().unwrap().size()).isEqualTo(2));
        assertThat(blackJackGame.getDealer().getHand().unwrap().size()).isEqualTo(2);
    }

    @Test
    void name() {
        BlackJackController.play();
    }
}
