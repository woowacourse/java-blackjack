package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.card.Deck;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.ParticipantResults;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackProcessManagerTest {

    BlackjackProcessManager blackjackProcessManager;

    @BeforeEach
    void init() {
        Deck deck = new Deck();
        blackjackProcessManager = new BlackjackProcessManager(deck, new ParticipantResults());
    }

    @Test
    void 손에_카드_1장을_쥐어준다() {
        // given
        Participant participant = new Player("히로", new Hand());
        blackjackProcessManager.giveMoreCard(participant);

        // when & then
        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    void 참가자들의_게임_결과를_저장한다() {
        // given
        Deck deck = new Deck();
        ParticipantResults participantResults = new ParticipantResults();
        Player player = new Player("히로", new Hand());
        Dealer dealer = new Dealer(new Hand());

        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();

        Participants participants = new Participants(List.of(player, dealer));

        ParticipantResult playerResult = new ParticipantResult(player, GameResultType.LOSE, 31);
        participantResults.add(playerResult);

        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck, participantResults);

        // when
        blackjackProcessManager.calculateAllResults(participants, gameRuleEvaluator);

        // then
        ParticipantResult result = participantResults.findResult(dealer);
        assertThat(result.getCountsOfResultTypes().getOrDefault(GameResultType.TIE, 0)).isEqualTo(1);
    }

    @Test
    void 참가자에게_시작_카드를_2장씩_분배한다() {
        // given
        Hand hand = new Hand();
        Player player = new Player("히로", hand);
        Participants participants = new Participants(List.of(player));

        // when
        blackjackProcessManager.giveStartingCards(participants);

        // then
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    void 참가자에게_추가적으로_카드를_분배한다() {
        // given
        Hand hand = new Hand();
        Player player = new Player("히로", hand);

        // when
        blackjackProcessManager.giveMoreCard(player);

        // then
        assertThat(player.getCards()).hasSize(1);
    }
}
