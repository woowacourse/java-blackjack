package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @DisplayName("초기 덱 생성 검증")
    @Test
    void checkInitialDeck() {
        Players players = Players.valueOf("a,b,c");
        Deck deck = new Deck(Card.getAllCards());
        BlackJackGame game = new BlackJackGame(players, deck);
        assertThat(game.getDeck().size()).isEqualTo(52);
    }
}
