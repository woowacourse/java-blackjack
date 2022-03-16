package blackjack.domain.game;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerMatchResultTest {

    @DisplayName("of 에 플레이어와 점수가 플레이어보다 낮은 딜러를 전달하면, ResultType.WIN 를 가진 PlayerMatchResult 를 반환한다.")
    @Test
    void of_returnsWin() {
        // given
        Player winnerPlayer = Player.of("winner", Hand.of(CLOVER10, CLOVER7));
        Dealer loserDealer = Dealer.of(Hand.of(CLOVER8, CLOVER7));

        // when
        PlayerMatchResult playerMatchResult = PlayerMatchResult.of(winnerPlayer, loserDealer);
        ResultType actual = playerMatchResult.getMatchResult();
        ResultType expected = ResultType.WIN;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("of 에 플레이어와 점수가 플레이어보다 높은 딜러를 전달하면, ResultType.LOSE 를 가진 PlayerMatchResult 를 반환한다.")
    @Test
    void of_returnsLose() {
        // given
        Player loserPlayer = Player.of("loser", Hand.of(CLOVER8, CLOVER7));
        Dealer winnerDealer = Dealer.of(Hand.of(CLOVER10, CLOVER7));

        // when
        PlayerMatchResult playerMatchResult = PlayerMatchResult.of(loserPlayer, winnerDealer);
        ResultType actual = playerMatchResult.getMatchResult();
        ResultType expected = ResultType.LOSE;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("of 에 점수가 같은 플레이어와 딜러를 전달하면, ResultType.DRAW 를 가진 PlayerMatchResult 를 반환한다.")
    @Test
    void of_returnsDraw() {
        // given
        Player player = Player.of("winner", Hand.of(CLOVER10, CLOVER7));
        Dealer dealerSameAsPlayer = Dealer.of(Hand.of(CLOVER9, CLOVER8));

        // when
        PlayerMatchResult playerMatchResult = PlayerMatchResult.of(player, dealerSameAsPlayer);
        ResultType actual = playerMatchResult.getMatchResult();
        ResultType expected = ResultType.DRAW;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
