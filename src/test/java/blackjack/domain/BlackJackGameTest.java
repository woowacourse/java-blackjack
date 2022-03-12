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
        BlackJackGame blackJackGame = new BlackJackGame(Arrays.asList("a", "b"));
        blackJackGame.distributeFirstCards();

        GamerDto dealerDto = blackJackGame.getDealerDto();
        List<GamerDto> playerDtos = blackJackGame.getPlayerDtos();

        assertThat(dealerDto.getCards().size()).isEqualTo(2);
        assertThat(playerDtos)
                .map(dto -> dto.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("플레이어에게 1장 배분한다.")
    void distributeCard() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("name"));
        blackJackGame.distributeCardToPlayer("name");

        GamerDto player = blackJackGame.getPlayerDtos().get(0);
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("name"));
        blackJackGame.distributeAdditionalToDealer();
        GamerDto dealer = blackJackGame.getDealerDto();
        int cardNumberSum = dealer.getCardNumberSum();
        assertThat(cardNumberSum).isGreaterThan(16);
    }

    @Test
    @DisplayName("중복된 이름 입력 시, 에러가 발생한다.")
    void validateDuplicationNames() {
        assertThatThrownBy(() -> new BlackJackGame(List.of("name", "name"))).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("플레이어 이름을 입력 받아, 플레이어를 반환한다.")
    void findPlayerByName() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("pobi", "jason"));
        GamerDto pobi = blackJackGame.getPlayerDto("pobi");
        assertThat(pobi.getName()).isEqualTo("pobi");
    }
}
