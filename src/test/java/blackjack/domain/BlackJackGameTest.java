package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackJackGameTest {
    @Test
    @DisplayName("딜러와 플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(Arrays.asList("a", "b"));
        blackJackGame.distributeFirstCards();

        Dealer dealerDto = blackJackGame.getDealer();
        List<Player> playerDtos = blackJackGame.getPlayers();

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

        Player player = blackJackGame.getPlayers().get(0);
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("name"));
        blackJackGame.distributeAdditionalToDealer();
        Dealer dealer = blackJackGame.getDealer();
        int cardNumberSum = dealer.getCardsNumberSum();
        assertThat(cardNumberSum).isGreaterThan(16);
    }

    @Test
    @DisplayName("중복된 이름 입력 시, 에러가 발생한다.")
    void validateDuplicationNames() {
        assertThatThrownBy(() -> {
            new BlackJackGame(List.of("name", "name"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }
}
