package domain;

import static org.assertj.core.api.Assertions.assertThat;

import controller.dto.JudgeResult;
import controller.dto.ParticipantOutcome;
import domain.card.Card;
import domain.card.Score;
import domain.card.Shape;
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
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.LOSE));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 승리한다.")
        @Test
        void playerDoesNotBusted() {
            Dealer dealer = createDealer(createBustedCards());
            Player player = createPlayer("pobi", createNormalWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.WIN));
        }
    }

    @DisplayName("딜러가 블랙잭인 경우")
    @Nested
    class DealerBlackJack {
        @DisplayName("참가자가 블랙잭인 경우 카드 개수가 더 적은 참가자가 승리한다.")
        @Test
        void playerBlackJackAndHaveMoreCard() {
            Dealer dealer = createDealer(createBlackJackWithTwoCards());
            Player player = createPlayer("pobi", createNormalWithThreeCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.WIN));
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
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.LOSE));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 딜러가 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = createDealer(createBlackJackWithTwoCards());
            Player player = createPlayer("pobi", createNormalWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.LOSE));
        }
    }

    @DisplayName("딜러의 점수가 21 미만인 경우")
    @Nested
    class DealerIsNotBlackJack {
        @DisplayName("참가자의 점수가 21인 경우 참가자가 승리한다.")
        @Test
        void playerIsBlackJack() {
            Dealer dealer = createDealer(createNormalWithTwoCards());
            Player player = createPlayer("pobi", createBlackJackWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.WIN));
        }

        @DisplayName("참가자의 점수가 21 미만인 경우 점수가 큰 사람이 승리한다.")
        @Test
        void playerIsNotBlackJack() {
            Dealer dealer = createDealer(createNormalWithTwoCards());
            Player player = createPlayer("pobi", createNormalWithThreeCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.WIN));
        }

        @DisplayName("참가자의 점수가 21 미만이고 딜러와 점수가 같은 경우 카드 개수가 적은 사람이 승리한다.")
        @Test
        void playerScoreEqualsToDealer() {
            Dealer dealer = createDealer(createNormalWithThreeCards());
            Player player = createPlayer("pobi", createSameScoreNormalWithTwoCards());

            Referee referee = createReferee(player, dealer);

            JudgeResult judgeResult = referee.judge();

            assertThat(judgeResult.results())
                    .hasSize(1)
                    .containsExactly(new ParticipantOutcome("pobi", Outcome.WIN));
        }
    }

    private Dealer createDealer(List<Card> cards) {
        Dealer dealer = new Dealer();
        dealer.pickCard(new Deck(cards), cards.size());
        return dealer;
    }

    private Player createPlayer(String name, List<Card> cards) {
        Player player = new Player(name);
        player.pickCard(new Deck(cards), cards.size());
        return player;
    }

    private Referee createReferee(final Player player, final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.add(player);
        return new Referee(new Participants(participants));
    }

    private List<Card> createBlackJackWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    private List<Card> createBlackJackWithThreeCards() {
        return List.of(
                new Card(Score.FIVE, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.ACE, Shape.DIAMOND));
    }

    private List<Card> createNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND));
    }

    private List<Card> createNormalWithThreeCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.SIX, Shape.HEART));
    }

    private List<Card> createSameScoreNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.NINE, Shape.DIAMOND));
    }

    private List<Card> createBustedCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.FIVE, Shape.DIAMOND),
                new Card(Score.EIGHT, Shape.HEART));
    }
}
