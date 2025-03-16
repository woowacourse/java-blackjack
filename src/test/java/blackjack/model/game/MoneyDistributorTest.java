package blackjack.model.game;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.PlayerName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyDistributorTest {

    @Test
    void 딜러가_블랙잭이_아닌_경우에_참가자_별_승무패_결과를_가지고_배당금을_계산한다() {
        // Given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.SPADE, CardType.NORMAL_5));
        Participant participant = new Participant(new PlayerName("프리"), new BettedMoney(10_000)); // 블랙잭
        participant.putCard(new Card(CardShape.SPADE, CardType.ACE));
        participant.putCard(new Card(CardShape.SPADE, CardType.KING));
        Participant participant2 = new Participant(new PlayerName("포비"), new BettedMoney(10_000)); // 승
        participant2.putCard(new Card(CardShape.SPADE, CardType.KING));
        Participant participant3 = new Participant(new PlayerName("제이슨"), new BettedMoney(10_000)); // 무
        participant3.putCard(new Card(CardShape.HEART, CardType.NORMAL_5));
        Participant participant4 = new Participant(new PlayerName("리사"), new BettedMoney(10_000)); // 패
        participant4.putCard(new Card(CardShape.SPADE, CardType.NORMAL_2));
        Participants participants = new Participants(List.of(participant, participant2, participant3, participant4));
        Map<Participant, ParticipantResult> winningResult = ParticipantResult.calculateParticipantResults(dealer, participants);

        // When
        Map<Participant, Long> winningMoneys = MoneyDistributor.calculateWinningMoneys(dealer, winningResult);

        // Then
        assertThat(winningMoneys).isEqualTo(Map.of(participant, 15_000L, participant2, 10_000L, participant3, 0L, participant4, -10_000L));
    }

    @Test
    void 딜러가_블랙잭인_경우에_참가자_별_승무패_결과를_가지고_배당금을_계산한다() {
        // Given
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.putCard(new Card(CardShape.DIAMOND, CardType.ACE));
        dealer.putCard(new Card(CardShape.DIAMOND, CardType.KING));
        Participant participant = new Participant(new PlayerName("프리"), new BettedMoney(10_000)); // 블랙잭(무)
        participant.putCard(new Card(CardShape.SPADE, CardType.ACE));
        participant.putCard(new Card(CardShape.SPADE, CardType.KING));
        Participant participant2 = new Participant(new PlayerName("제이슨"), new BettedMoney(10_000)); // 패(21)
        participant2.putCard(new Card(CardShape.HEART, CardType.NORMAL_5));
        participant2.putCard(new Card(CardShape.HEART, CardType.NORMAL_7));
        participant2.putCard(new Card(CardShape.HEART, CardType.NORMAL_9));
        Participant participant3 = new Participant(new PlayerName("리사"), new BettedMoney(10_000)); // 패
        participant3.putCard(new Card(CardShape.SPADE, CardType.NORMAL_2));
        Participants participants = new Participants(List.of(participant, participant2, participant3));
        Map<Participant, ParticipantResult> winningResult = ParticipantResult.calculateParticipantResults(dealer, participants);

        // When
        Map<Participant, Long> winningMoneys = MoneyDistributor.calculateWinningMoneys(dealer, winningResult);

        // Then
        assertThat(winningMoneys).isEqualTo(Map.of(participant, 0L, participant2, -10_000L, participant3, -10_000L));
    }

    @Test
    void 딜러의_수익을_계산한다() {
        // Given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.SPADE, CardType.NORMAL_5));
        Participant participant = new Participant(new PlayerName("프리"), new BettedMoney(10_000)); // 블랙잭
        participant.putCard(new Card(CardShape.SPADE, CardType.ACE));
        participant.putCard(new Card(CardShape.SPADE, CardType.KING));
        Participant participant2 = new Participant(new PlayerName("포비"), new BettedMoney(10_000)); // 승
        participant2.putCard(new Card(CardShape.SPADE, CardType.KING));
        Participant participant3 = new Participant(new PlayerName("제이슨"), new BettedMoney(10_000)); // 무
        participant3.putCard(new Card(CardShape.HEART, CardType.NORMAL_5));
        Participant participant4 = new Participant(new PlayerName("리사"), new BettedMoney(10_000)); // 패
        participant4.putCard(new Card(CardShape.SPADE, CardType.NORMAL_2));
        Participants participants = new Participants(List.of(participant, participant2, participant3, participant4));
        Map<Participant, ParticipantResult> winningResult = ParticipantResult.calculateParticipantResults(dealer, participants);
        Map<Participant, Long> winningMoneys = MoneyDistributor.calculateWinningMoneys(dealer, winningResult);

        // When & Then
        assertThat(MoneyDistributor.calculateDealerMoney(winningMoneys)).isEqualTo(-15_000L);
    }
}
