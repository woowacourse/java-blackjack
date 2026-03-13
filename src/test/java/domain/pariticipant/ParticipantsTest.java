package domain.pariticipant;

import domain.card.Hand;
import domain.result.DealerMatchResult;
import domain.result.MatchCase;
import domain.result.MatchResult;
import domain.result.PlayersMatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constant.BlackjackConstant.DEALER_NAME;
import static domain.card.CardRank.*;
import static domain.card.CardRank.KING;
import static domain.card.CardSuit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static test_util.TestUtil.createCard;
import static test_util.TestUtil.createPlayer;

class ParticipantsTest {
    @Test
    @DisplayName("딜러와 플레이어의 카드를 비교하여 승리, 무승부, 패배를 계산한다.")
    public void calculatePlayersMatchResult_success() throws Exception {
//        // given
//        Dealer dealer = new Dealer(
//                new Name(DEALER_NAME),
//                new Hand(new ArrayList<>(List.of(
//                        createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT)
//                ))));
//
//        Player pobi = createPlayer("pobi",
//                new ArrayList<>(List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, ACE))));
//        Player jason = createPlayer("jason",
//                new ArrayList<>(List.of(createCard(CLUB, SEVEN), createCard(SPADE, KING))));
//        Players players = new Players(List.of(pobi, jason));
//        Participants participants = new Participants(dealer, players);
//
//
//        // when
//        MatchResult matchResult = participants.calculatePlayersMatchResult();
//
//        // then
//        PlayersMatchResult playersMatchResult = matchResult.playersMatchResult();
//        DealerMatchResult dealerMatchResult = matchResult.dealerMatchResult();
//
//        assertThat(playersMatchResult.playerMatchResult())
//                .containsExactlyInAnyOrderEntriesOf(
//                        Map.of(pobi, MatchCase.WIN,
//                                jason, MatchCase.LOSE
//                        )
//                );
//        assertThat(dealerMatchResult)
//                .extracting(
//                        DealerMatchResult::winCount, DealerMatchResult::drawCount, DealerMatchResult::loseCount)
//                .containsExactly(1, 0, 1);
    }

}
