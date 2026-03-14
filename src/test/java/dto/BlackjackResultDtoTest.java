package dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackResultDtoTest {

    @Test
    void of로_생성하면_딜러와_플레이어_결과를_포함한다() {
        // given
        ParticipantDto dealerResult = new ParticipantDto("딜러", List.of("10하트", "7스페이드"), 17);
        List<ParticipantDto> playerResults = List.of(
                new ParticipantDto("jacob", List.of("A클로버", "K다이아몬드"), 21),
                new ParticipantDto("seoye", List.of("9하트", "7클로버"), 16)
        );

        // when
        BlackjackResultDto actual = BlackjackResultDto.of(dealerResult, playerResults);

        // then
        assertThat(actual.dealerResultDto()).isEqualTo(dealerResult);
        assertThat(actual.playerResultDtoList()).containsExactlyElementsOf(playerResults);
    }
}
