package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

class BlackJackGameTest {
    @Test
    @DisplayName("딜러와 플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackGame blackJackGame = new BlackJackGame(
            Arrays.asList("a", "b"), new CardFactory(Card.getCards()));

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
        BlackJackGame blackJackGame = new BlackJackGame(List.of("name"), new CardFactory(Card.getCards()));
        blackJackGame.askDealerHitOrStay();
        GamerDto dealer = blackJackGame.getDealerDto();
        int cardNumberSum = dealer.getCardNumberSum();
        assertThat(cardNumberSum).isGreaterThan(16);
    }

    @Test
    @DisplayName("플레이어 카드 숫자 합과 딜러의 합이 같으면 무승부")
    void createResultDraw() {
        BlackJackGame blackJackGame = new BlackJackGame(
            List.of("name"), () -> Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));

        blackJackGame.askPlayerHitOrStay(answer -> false, dto -> {});
        GameResultDto result = blackJackGame.createResult();

        Map<BlackJackResult, Integer> dealerResult = result.getDealerResult();
        Map<String, BlackJackResult> playerResults = result.getPlayerResults();

        assertThat(playerResults.get("name")).isEqualTo(BlackJackResult.DRAW);
        assertThat(dealerResult.get(BlackJackResult.DRAW)).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 버스트이면 패배")
    void createResultLose() {
        BlackJackGame blackJackGame = new BlackJackGame(
            List.of("name"), () -> Card.getInstance(CardShape.CLOVER, CardNumber.EIGHT));

        blackJackGame.askPlayerHitOrStay(answer -> true, dto -> {});
        GameResultDto result = blackJackGame.createResult();

        Map<BlackJackResult, Integer> dealerResult = result.getDealerResult();
        Map<String, BlackJackResult> playerResults = result.getPlayerResults();

        assertThat(playerResults.get("name")).isEqualTo(BlackJackResult.LOSE);
        assertThat(dealerResult.get(BlackJackResult.WIN)).isEqualTo(1);
    }
}
