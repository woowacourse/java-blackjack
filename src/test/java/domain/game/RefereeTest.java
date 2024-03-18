package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static util.CardsSupplier.createBlackJackCards;
import static util.CardsSupplier.createBustedCards;
import static util.CardsSupplier.createNaturalBlackJackCards;
import static util.CardsSupplier.createNineteenScoreNormalWithTwoCards;
import static util.CardsSupplier.createNineteenScoreWithThreeCards;
import static util.CardsSupplier.createSixteenScoreWithTwoCards;
import static util.ParticipantSupplier.createDealer;
import static util.ParticipantSupplier.createPlayer;

import controller.dto.response.PlayerOutcome;
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

    @DisplayName("참가자의 점수가 21을 초과한 경우")
    @Nested
    class PlayerBusted {
        @DisplayName("딜러가 21을 초과해도 패배한다.")
        @Test
        void dealerBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createBustedCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }

        @DisplayName("딜러가 블랙잭이면 패배한다.")
        @Test
        void dealerBlackJack() {
            Dealer dealer = createDealer(createBlackJackCards());
            Player player = createPlayer("pobi", createBustedCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }

        @DisplayName("딜러가 21 미만이면 패배한다.")
        @Test
        void dealerNotBusted() {
            Dealer dealer = createDealer(createNineteenScoreNormalWithTwoCards());
            Player player = createPlayer("pobi", createBustedCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }
    }

    @DisplayName("참가자의 점수가 21 미만인 경우")
    @Nested
    class DealerNotBusted {
        @DisplayName("딜러의 점수가 21을 초과한 경우 참가자가 승리한다.")
        @Test
        void dealerBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }

        @DisplayName("딜러의 점수가 21인 경우 딜러가 승리한다.")
        @Test
        void dealerBlackJack() {
            Dealer dealer = createDealer(createBlackJackCards());
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }

        @DisplayName("딜러의 점수가 21 미만이고 참가자보다 작은 경우 참가자가 승리한다.")
        @Test
        void dealerHasMoreScoreThanPlayer() {
            Dealer dealer = createDealer(createSixteenScoreWithTwoCards());
            Player player = createPlayer("pobi", createNineteenScoreWithThreeCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }

        @DisplayName("딜러의 점수가 21 미만이고 참가자보다 큰 경우 딜러가 승리한다.")
        @Test
        void dealerHasLessScoreThanPlayer() {
            Dealer dealer = createDealer(createNineteenScoreWithThreeCards());
            Player player = createPlayer("pobi", createSixteenScoreWithTwoCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }

        @DisplayName("참가자와 딜러의 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = createDealer(createNineteenScoreWithThreeCards());
            Player player = createPlayer("pobi", createNineteenScoreNormalWithTwoCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }
    }


    @DisplayName("참가자의 카드의 합이 21이면서 카드가 2개인 경우")
    @Nested
    class PlayerNaturalBlackJack {
        @DisplayName("딜러의 카드가 21 미만인 경우 참가자가 블랙잭으로 승리한다.")
        @Test
        void playerNaturalBlackJack() {
            Dealer dealer = createDealer(createNineteenScoreWithThreeCards());
            Player player = createPlayer("pobi", createNaturalBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.BLACKJACK));
        }

        @DisplayName("딜러의 카드의 합이 21이면서 카드가 2개인 경우 무승부이다.")
        @Test
        void dealerNaturalBlackJack() {
            Dealer dealer = createDealer(createNaturalBlackJackCards());
            Player player = createPlayer("pobi", createNaturalBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.TIE));
        }

        @DisplayName("딜러의 카드의 합이 21이지만 카드가 3개인 경우 참가자가 블랙잭으로 승리한다.")
        @Test
        void dealerBlackJack() {
            Dealer dealer = createDealer(createBlackJackCards());
            Player player = createPlayer("pobi", createNaturalBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.BLACKJACK));
        }

        @DisplayName("딜러의 카드의 합이 21을 초과한 경우 참가자가 블랙잭으로 승리한다.")
        @Test
        void dealerBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createNaturalBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.BLACKJACK));
        }
    }

    @DisplayName("참가자의 카드의 합이 21이면서 카드가 3개인 경우")
    @Nested
    class PlayerBlackJack {
        @DisplayName("딜러의 카드가 21 미만인 경우 참가자가 승리한다.")
        @Test
        void playerNaturalBlackJack() {
            Dealer dealer = createDealer(createNineteenScoreWithThreeCards());
            Player player = createPlayer("pobi", createBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }

        @DisplayName("딜러의 카드의 합이 21이면서 카드가 2개인 경우 패배한다.")
        @Test
        void dealerNaturalBlackJack() {
            Dealer dealer = createDealer(createNaturalBlackJackCards());
            Player player = createPlayer("pobi", createBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.LOSE));
        }

        @DisplayName("딜러의 카드의 합이 21이지만 카드가 3개인 경우 카드가 적은 사람이 승리한다.")
        @Test
        void dealerBlackJack() {
            Dealer dealer = createDealer(createBlackJackCards());
            Player player = createPlayer("pobi", createBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }

        @DisplayName("딜러의 카드의 합이 21을 초과한 경우 참가자가 승리한다.")
        @Test
        void dealerBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createBlackJackCards());

            Referee referee = createReferee(player, dealer);

            List<PlayerOutcome> outcomes = referee.judge();

            assertThat(outcomes)
                    .hasSize(1)
                    .containsExactly(new PlayerOutcome(new Player("pobi", 1), Outcome.WIN));
        }
    }

    public Referee createReferee(final Player player, final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.add(player);
        return new Referee(new Participants(participants));
    }
}
