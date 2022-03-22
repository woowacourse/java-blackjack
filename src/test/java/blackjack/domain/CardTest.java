package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    @DisplayName("52개의 생성된 카드 개수가 올바른지 확인한다.")
    @Test
    void select_cards() {
        List<Card> cards = Card.getDeck();

        assertThat(cards.size()).isEqualTo(52);
    }

    @DisplayName("카드 이름이 정상적으로 출력되는지 확인한다.")
    @Test
    void print_name() {
        Card card = Card.of(CardNumber.ACE, CardShape.SPADE);

        assertThat(card.getName()).isEqualTo("A스페이드");
    }
}
