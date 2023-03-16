package blackjack.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.FixtureCard.스페이드_Q;

class CardDtoTest {

    @Test
    void 카드_DTO는_카드의_모양과_숫자를_가진다() {
        // given
        CardDto cardDto = new CardDto(스페이드_Q);

        // then
        Assertions.assertThat(cardDto.getNumber()).isEqualTo("Q");
        Assertions.assertThat(cardDto.getShape()).isEqualTo("스페이드");
    }
}
