package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드덱 생성 시, 카드덱은 총 52장의 카드로 이루어져있는 지 테스트")
    public void cardDeckSize() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.size()).isEqualTo(52);
    }

}
