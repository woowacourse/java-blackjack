package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantGroupTest {
    @DisplayName("of 메소드에 Dealer 와 List<Player> 를 전달하여 ParticipantGroup 의 인스턴스를 생성할 수 있다.")
    @Test
    void of() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER2, CLOVER3));
        List<Player> players = List.of(
                Player.of("player1", Hand.of(CLOVER4, CLOVER5)),
                Player.of("player2", Hand.of(CLOVER6, CLOVER7)));

        // when
        ParticipantGroup participantGroup = ParticipantGroup.of(dealer, players);

        // then
        assertThat(participantGroup).isNotNull();
    }
}
