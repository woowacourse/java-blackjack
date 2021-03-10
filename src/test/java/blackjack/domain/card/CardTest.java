package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardTest {
    @Test
    @DisplayName("카드 생성")
    void create_cards() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE))
            .isSameAs(Card.from(Suits.DIAMOND, Denominations.ACE));
    }

    @Test
    @DisplayName("카드 생성2 - 생성자에 type 순서 다르게 입력")
    void create_cards3() {
        assertThat(Card.from(Suits.DIAMOND, Denominations.ACE))
            .isSameAs(Card.from(Denominations.ACE, Suits.DIAMOND));
    }

}
