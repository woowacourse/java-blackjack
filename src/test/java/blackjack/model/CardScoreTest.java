package blackjack.model;

import blackjack.model.card.CardScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardScoreTest {

    @Test
    @DisplayName("카드 점수 비교 - 둘다 stand 상태")
    void card_score_compare_when_stand() {
        //given
        CardScore cardScore1 = new CardScore(20, ResultState.STAND);
        CardScore cardScore2 = new CardScore(18, ResultState.STAND);

        // when
        int resultCompare = cardScore1.compareTo(cardScore2);

        //then
        assertThat(resultCompare).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 점수 비교 - 한명 blakcjack, 한 명 stand 상태")
    void card_score_compare_when_blackjack_stand() {
        //given
        CardScore cardScore1 = new CardScore(21, ResultState.BLACKJACK);
        CardScore cardScore2 = new CardScore(18, ResultState.STAND);

        // when
        int resultCompare = cardScore1.compareTo(cardScore2);

        //then
        assertThat(resultCompare).isPositive();
    }

    @Test
    @DisplayName("카드 점수 비교 - 둘 다 blakcjack 상태")
    void card_score_compare_when_blackjack_draw() {
        //given
        CardScore cardScore1 = new CardScore(21, ResultState.BLACKJACK);
        CardScore cardScore2 = new CardScore(21, ResultState.BLACKJACK);

        // when
        int resultCompare = cardScore1.compareTo(cardScore2);

        //then
        assertThat(resultCompare).isEqualTo(0);
    }

    @Test
    @DisplayName("카드 점수 비교 - 딜러와 플레이어 모두 버스트 : 딜러가 승리")
    void card_score_compare_when_bust() {
        //given
        CardScore playerScore = new CardScore(25, ResultState.PLAYER_BUST);
        CardScore dealerScore = new CardScore(24, ResultState.DEALER_BUST);

        //then
        assertThat(dealerScore.compareTo(playerScore)).isPositive();
    }
}
