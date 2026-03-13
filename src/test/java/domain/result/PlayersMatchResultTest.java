package domain.result;

import domain.pariticipant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.card.CardRank.*;
import static domain.card.CardSuit.*;
import static domain.result.MatchCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static test_util.TestUtil.createCard;
import static test_util.TestUtil.createPlayer;

class PlayersMatchResultTest {

    @Test
    @DisplayName("딜러의 매치 결과를 계산한다.")
    public void calculateDealerMatchResult_success() throws Exception {
        // given
        Player pobi = createPlayer("pobi",
                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, ACE)));
        Player jason = createPlayer("jason",
                List.of(createCard(CLUB, SEVEN), createCard(SPADE, KING))
        );
        Player lisa = createPlayer("lisa",
                List.of(createCard(CLUB, SEVEN), createCard(SPADE, ACE))
        );
        PlayersMatchResult playersMatchResult = new PlayersMatchResult(Map.of(pobi, WIN, jason, LOSE, lisa, DRAW));

        // when
        DealerMatchResult dealerMatchResult = playersMatchResult.calculateDealerMatchResult();

        // then
        assertThat(dealerMatchResult)
                .extracting(
                        DealerMatchResult::winCount,
                        DealerMatchResult::drawCount,
                        DealerMatchResult::loseCount
                ).containsExactly(1, 1, 1);
    }

}
