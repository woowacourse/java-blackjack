package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @DisplayName("카드덱 생성자 테스트")
    @Test
    void create() {
        CardDeck cardDeck = CardDeck.initShuffled();

        assertThat(cardDeck).isNotNull();
    }

    @DisplayName("카드덱 생성 시 52장의 카드가 생성되었는지 테스트")
    @Test
    void create52() {
        CardDeck cardDeck = CardDeck.initShuffled();

        assertThat(cardDeck.leftSize()).isEqualTo(52);
    }

    @DisplayName("2장 분배 테스트")
    @Test
    void distributeTwoCards() {
        CardDeck cardDeck = CardDeck.initShuffled();
        List<Card> cards = cardDeck.distribute(2);

        assertThat(cards.size()).isEqualTo(2);
    }

    @DisplayName("1장 분배 테스트")
    @Test
    void distributeOneCard() {
        CardDeck cardDeck = CardDeck.initShuffled();
        List<Card> cards = cardDeck.distribute(1);

        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("53장 분배 실패 테스트")
    @Test
    void overCapacityDistributeCard() {
        CardDeck cardDeck = CardDeck.initShuffled();
        assertThatThrownBy(() -> cardDeck.distribute(53))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 부족합니다.");
    }
}
