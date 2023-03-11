package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantGeneratorTest {


    @DisplayName("딜러 생성에 성공한다.")
    @Test
    void create_dealer_success() {
        // when && then
        assertThatNoException().isThrownBy(() -> ParticipantGenerator.createEmptyCardDealer());
    }

    @DisplayName("생성된 딜러는 기본적으로 0장의 카드를 가지고 있다.")
    @Test
    void dealer_drawn_cards_size_zero() {
        // given
        Dealer dealer = ParticipantGenerator.createEmptyCardDealer();
        // when
        int actual = dealer.getDrawnCards()
                .size();
        // then
        assertThat(actual).isEqualTo(0);
    }
}
