package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void createCard() {
        // when
        Pattern pattern = Pattern.DIAMOND;
        Denomination denomination = Denomination.ACE;
        Card card = new Card(pattern, denomination);

        // then
        assertAll(
            () -> assertThat(card).isNotNull(),
            () -> assertThat(card.getPattern()).isEqualTo(pattern),
            () -> assertThat(card.getDenomination()).isEqualTo(denomination)
        );
    }
}
