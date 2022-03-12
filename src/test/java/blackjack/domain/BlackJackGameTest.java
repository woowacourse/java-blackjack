package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.dto.GamerDto;

class BlackJackGameTest {
    @Test
    @DisplayName("딜러와 플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackGame blackJackGame = BlackJackGame.start(Arrays.asList("a", "b"));

        GamerDto dealerDto = blackJackGame.getDealerDto();
        List<GamerDto> playerDtos = blackJackGame.getPlayerDtos();

        assertThat(dealerDto.getCards().size()).isEqualTo(2);
        assertThat(playerDtos)
                .map(dto -> dto.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackGame blackJackGame = BlackJackGame.start(List.of("name"));
        blackJackGame.distributeAdditionalToDealer();
        GamerDto dealer = blackJackGame.getDealerDto();
        int cardNumberSum = dealer.getCardNumberSum();
        assertThat(cardNumberSum).isGreaterThan(16);
    }
}
