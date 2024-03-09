package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.createByRandomOrder();
    }

    @Test
    @DisplayName("카드 덱을 생성한다.")
    void createDeck() {
        assertThat(deck.getDeck()).hasSize(52);
    }

    @Test
    @DisplayName("인원 수만큼 카드 목록을 생성한다.")
    void distributeInitialCardByPlayerCount() {
        assertThat(deck.distributeInitialCard(4)).hasSize(4);
    }

    @Test
    @DisplayName("카드 한 세트(52장)을 다 소진할 경우 예외가 발생한다.")
    void throwErrorWhenNoCard() {
        Assertions.assertThatThrownBy(() -> deck.distributeInitialCard(27))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카드가 부족합니다.");
    }

    @Test
    @DisplayName("Deck에서 가장 앞에 있는 카드를 반환한다.")
    void distribute() {
        Card expected = deck.getDeck().getFirst();
        assertThat(deck.distribute()).isEqualTo(expected);
    }

}
