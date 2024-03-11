package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static util.CardsSupplier.createBlackJackWithThreeCards;
import static util.CardsSupplier.createBlackJackWithTwoCards;
import static util.CardsSupplier.createBustedCards;
import static util.CardsSupplier.createNineteenScoreNormalWithTwoCards;
import static util.CardsSupplier.createNineteenScoreWithThreeCards;
import static util.CardsSupplier.createSixteenScoreWithTwoCards;
import static util.ParticipantSupplier.createDealer;
import static util.ParticipantSupplier.createPlayer;

import controller.dto.JudgeResult;
import controller.dto.PlayerOutcome;
import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @DisplayName("딜러의 점수가 21을 초과한 경우")
    @Nested
    class dealerBusted {
        @DisplayName("모든 참가자는 21을 초과하면 패한다.")
        @Test
        void playerBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createBustedCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.LOSE));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.WIN));
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class DealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 더 적은 참가자가 승리한다.")
        @Test
        void playerBlackJackAndHaveMoreCard() {
            Dealer dealer = createDealer(createBlackJackWithThreeCards());
            Player player = createPlayer("pobi", createBlackJackWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.WIN));
        }

        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 더 적은 딜러가 승리한다.")
        @Test
        void playerBlackJackAndHaveLessCard() {
            Dealer dealer = createDealer(createBlackJackWithTwoCards());
            Player player = createPlayer("pobi", createBlackJackWithThreeCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.LOSE));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = createDealer(createBlackJackWithTwoCards());
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.LOSE));
        }
    }

    @DisplayName("딜러의 점수가 21 미만인 경우")
    @Nested
    class DealerIsNotBlackJack {
        @DisplayName("참가자의 점수가 21인 경우 참가자가 승리한다.")
        @Test
        void playerIsBlackJack() {
            Dealer dealer = createDealer(createSixteenScoreWithTwoCards());
            Player player = createPlayer("pobi", createBlackJackWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.WIN));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = createDealer(createSixteenScoreWithTwoCards());
            Player player = createPlayer("pobi", createNineteenScoreWithThreeCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.WIN));
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = createDealer(createNineteenScoreWithThreeCards());
            Player player = createPlayer("pobi", createNineteenScoreNormalWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome("pobi", Outcome.WIN));
        }
    }

    public Referee createReferee(final Player player, final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.add(player);
        return new Referee(new Participants(participants));
    }
}
