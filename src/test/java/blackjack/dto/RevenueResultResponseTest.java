package blackjack.dto;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
import static blackjack.utils.ParticipantsCreationUtils.createPlayerWithDenominations;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.RevenueResult;
import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenueResultResponseTest {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    @DisplayName("딜러가 1승 1패일 경우에 대해 결과를 출력한다")
    void when_dealer_1win_1lose() {
        // given
        Dealer dealer = createDealerWithDenominations(THREE, NINE, EIGHT);
        Player pobi = createPlayerWithDenominations("pobi", TWO, EIGHT, ACE);
        Player json = createPlayerWithDenominations("jason", SEVEN, KING);
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(pobi, json));
        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(pobi.getName(), "10000"));
        bettingMonies.add(new BettingMoney(json.getName(), "20000"));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // when
        RevenueResultResponse resultResponse = RevenueResultResponse.from(revenueResult);
        String dealerRevenueMessage = resultResponse.getDealerRevenueMessage();
        String playersRevenueMessage = resultResponse.getPlayersRevenueMessage()
                .stream()
                .collect(joining(LINE_SEPARATOR));

        // then
        assertThat(dealerRevenueMessage).isEqualTo("딜러: 10000");
        assertThat(playersRevenueMessage).isEqualTo("pobi: 10000" + LINE_SEPARATOR + "jason: -20000");
    }

    @Test
    @DisplayName("플레이어가 각각 블랙잭, 무승부일 경우에 대하여 결과를 출력한다")
    void when_players_1blackjack_1draw() {
        // given
        Dealer dealer = createDealerWithDenominations(THREE, NINE, EIGHT);
        Player pobi = createPlayerWithDenominations("pobi", ACE, QUEEN);
        Player json = createPlayerWithDenominations("jason", JACK, TEN);
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(pobi, json));
        List<BettingMoney> bettingMonies = new ArrayList<>();
        bettingMonies.add(new BettingMoney(pobi.getName(), "30000"));
        bettingMonies.add(new BettingMoney(json.getName(), "40000"));
        RevenueResult revenueResult = RevenueResult.of(scoreBoard, bettingMonies);

        // when
        RevenueResultResponse resultResponse = RevenueResultResponse.from(revenueResult);
        String dealerRevenueMessage = resultResponse.getDealerRevenueMessage();
        String playersRevenueMessage = resultResponse.getPlayersRevenueMessage()
                .stream()
                .collect(joining(LINE_SEPARATOR));

        // then
        assertThat(dealerRevenueMessage).isEqualTo("딜러: -45000");
        assertThat(playersRevenueMessage).isEqualTo("pobi: 45000" + LINE_SEPARATOR + "jason: 0");
    }
}
