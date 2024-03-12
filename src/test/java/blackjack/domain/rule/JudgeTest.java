package blackjack.domain.rule;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.Players;
import fixture.DealerFixture;
import fixture.HandFixture;
import fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JudgeTest {

    @DisplayName("플레이어가 21을 초과하면 플레이어는 패배하고 딜러는 승리한다.")
    @Test
    void testPlayerBust() {
        // given
        Dealer dealer = new Dealer(new Hand());

        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));
        Player player = new Player(hand, new PlayerName("pobi"));

        Participants participants = new Participants(new Players(List.of(player)), dealer);

        // when
        Judge judge = new Judge(participants);

        // then
        Result dealerResult = judge.getResults().get(dealer);
        Result playerResult = judge.getResults().get(player);

        assertAll(
                () -> assertThat(dealerResult.getWinCount()).isEqualTo(1),
                () -> assertThat(dealerResult.getLoseCount()).isEqualTo(0),

                () -> assertThat(playerResult.getWinCount()).isEqualTo(0),
                () -> assertThat(playerResult.getLoseCount()).isEqualTo(1)
        );
    }

    @DisplayName("플레이어가 21을 초과하지 않고 딜러가 21을 초과하면 플레이어는 승리하고 딜러는 패배한다.")
    @Test
    void testDealerBust() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));
        Dealer dealer = new Dealer(hand);

        Player player = new Player(new Hand(), new PlayerName("pobi"));

        Participants participants = new Participants(new Players(List.of(player)), dealer);

        // when
        Judge judge = new Judge(participants);

        // then
        Result dealerResult = judge.getResults().get(dealer);
        Result playerResult = judge.getResults().get(player);

        assertAll(
                () -> assertThat(dealerResult.getWinCount()).isEqualTo(0),
                () -> assertThat(dealerResult.getLoseCount()).isEqualTo(1),

                () -> assertThat(playerResult.getWinCount()).isEqualTo(1),
                () -> assertThat(playerResult.getLoseCount()).isEqualTo(0)
        );
    }

    @DisplayName("플레이어와 딜러가 21을 초과하지 않으면 플레이어가 딜러보다 합이 높을 때 승리한다.")
    @Test
    void testJudge() {
        // given
        Dealer dealer = DealerFixture.createDealer();
        Player pobi = PlayerFixture.createPobi();
        Player jason = PlayerFixture.createJason();

        Participants participants = new Participants(new Players(List.of(pobi, jason)), dealer);

        // when
        Judge judge = new Judge(participants);

        // then
        Result dealerResult = judge.getResults().get(dealer);
        Result pobiResult = judge.getResults().get(pobi);
        Result jasonResult = judge.getResults().get(jason);

        assertAll(
                () -> assertThat(dealerResult.getWinCount()).isEqualTo(1),
                () -> assertThat(dealerResult.getLoseCount()).isEqualTo(1),

                () -> assertThat(pobiResult.getWinCount()).isEqualTo(1),
                () -> assertThat(pobiResult.getLoseCount()).isEqualTo(0),

                () -> assertThat(jasonResult.getWinCount()).isEqualTo(0),
                () -> assertThat(jasonResult.getLoseCount()).isEqualTo(1)
        );
    }
}
