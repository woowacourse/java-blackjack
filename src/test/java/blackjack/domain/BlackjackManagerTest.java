package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.view.dto.ParticipantDto;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackServiceTest {

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        players = new Players(Arrays.asList("pobi", "jason"));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        BlackjackService blackjackService = new BlackjackService(dealer, players);
        blackjackService.initDrawCards();

        assertThat(players.toList()
            .stream()
            .filter(player -> player.getHand().size() == 2)
            .count())
            .isEqualTo(2);
    }

    @Test
    @DisplayName("게임 플레이 결과 DTO 생성")
    void testCreateResultDto() {
        BlackjackService blackjackService = new BlackjackService(dealer, players);
        blackjackService.initDrawCards();
        ParticipantDto participantDto = BlackjackService.getGameResult(dealer, players);

        assertThat(participantDto).isInstanceOf(ParticipantDto.class);
    }
}
