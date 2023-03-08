package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantTest {

    @Test
    @DisplayName("createDealer()는 호출하면, 딜러를 생성한다")
    void createDealer_whenCall_thenSuccess() {
        final Dealer dealer = assertDoesNotThrow(Participant::createDealer);
        assertThat(dealer)
                .isExactlyInstanceOf(Dealer.class);
    }
}
