package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @DisplayName("Deck 객체를 생성한다.")
    @Test
    public void createDeck() {
        Deck deck = new Deck(CardFactory.make());

        assertThat(deck).isInstanceOf(Deck.class);
    }

    @DisplayName("카드를 두장 뽑는다 - 시작 단계")
    @Test
    public void popTwoCardsStart(){
        Deck deck = new Deck(CardFactory.make());
        GameStatus gameStatus = new GameStatus(true);

        assertThat(deck.pop(gameStatus).size()).isEqualTo(2);
    }

    @DisplayName("카드를 한장 뽑는다 - 진행 단계")
    @Test
    public void popTwoCardsOngoing(){
        Deck deck = new Deck(CardFactory.make());
        GameStatus gameStatus = new GameStatus(false);

        assertThat(deck.pop(gameStatus).size()).isEqualTo(1);
    }
}
