package blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.dto.PlayerCardsDto;
import blackjack.dto.StartCardsDto;
import blackjack.exception.NeedRetryException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임을 시작하면 딜러와 플레이어에게 2장씩 분배하고 딜러의 카드 한장은 비밀이다.")
    @Test
    void start() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(List.of("kirby", "pobi"));

        // when
        final StartCardsDto startCardsDto = blackjackGame.start();

        // then
        final List<PlayerCardsDto> playerCardsDtos = startCardsDto.playersCards();
        final PlayerCardsDto dealerCardsDto = startCardsDto.dealerCards();
        assertAll(
                () -> assertThat(playerCardsDtos).hasSize(2),
                () -> assertThat(playerCardsDtos.get(0).name()).isEqualTo("kirby"),
                () -> assertThat(playerCardsDtos.get(1).name()).isEqualTo("pobi"),
                () -> assertThat(playerCardsDtos.get(0).cards()).hasSize(2),
                () -> assertThat(playerCardsDtos.get(1).cards()).hasSize(2),
                () -> assertThat(dealerCardsDto.cards()).hasSize(1)
        );
    }

    @DisplayName("'딜러' 이름으로 블랙잭 게임을 할 수 없다.")
    @Test
    void validateDealName() {
        final List<String> names = List.of("딜러", "kirby");

        assertThatThrownBy(() -> new BlackjackGame(names))
                .isInstanceOf(NeedRetryException.class)
                .hasMessage("딜러를 이름으로 사용할 수 없습니다.");
    }
}
