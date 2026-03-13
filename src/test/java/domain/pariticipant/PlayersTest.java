package domain.pariticipant;

import domain.result.MatchCase;
import domain.result.PlayersMatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constant.BlackjackConstant.DEALER_NAME;
import static domain.card.CardRank.*;
import static domain.card.CardSuit.*;
import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.*;
import static test_util.TestUtil.*;

class PlayersTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 7})
    @DisplayName("플레이어는 최소 1명 최대 7명이어야 한다.")
    public void players_success(int playerNum) throws Exception {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(createPlayer("name" + i, new ArrayList<>()));
        }

        // when then
        assertThatCode(() -> new Players(players))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 8})
    @DisplayName("플레이어는 1명 미만이거나 7명 초과면 예외가 발생한다..")
    public void players_fail(int playerNum) throws Exception {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(createPlayer("name" + i, new ArrayList<>()));
        }

        // when then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
    }

    @Test
    @DisplayName("플레이어들의 매치 결과를 계산한다.")
    public void calculateMatchResult_success() throws Exception {
        // given
        Player pobi = createPlayer("pobi",
                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, ACE)));
        Player jason = createPlayer("jason",
                List.of(createCard(CLUB, SEVEN), createCard(SPADE, KING))
        );
        Players players = new Players(List.of(pobi, jason));

        Dealer dealer = creteDealer(DEALER_NAME,
                List.of(createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT)));

        // when
        PlayersMatchResult playersMatchResult = players.calculateMatchResult(dealer);

        // then
        Map<Player, MatchCase> playerMatchCaseMap = playersMatchResult.playerMatchResult();
        assertThat(playerMatchCaseMap).hasSize(2)
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(pobi, MatchCase.WIN, jason, MatchCase.LOSE)
                );
    }
}
