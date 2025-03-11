package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantResultTest {

    @Test
    void 플레이어가_버스트가_아니고_참가자가_패배인_경우() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant("프리");
        Participant participant2 = new Participant("벡터");

        Participants participants = new Participants(List.of(participant1, participant2));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_2));

        // when
        GameResult gameResult = new GameResult(dealer, participants);
        ParticipantResult result = gameResult.getWinLoseResult().get(participant1);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 참가자가_버스트고_딜러가_버스트가_아닌_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant("프리");
        Participant participant2 = new Participant("벡터");

        Participants participants = new Participants(List.of(participant1, participant2));

        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        GameResult gameResult = new GameResult(dealer, participants);
        ParticipantResult result = gameResult.getWinLoseResult().get(participant1);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);

    }

    @Test
    void 딜러가_버스트고_참가자가_버스트가_아닌_경우_참가자승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant("프리");
        Participant participant2 = new Participant("벡터");

        Participants participants = new Participants(List.of(participant1, participant2));

        participant1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        GameResult gameResult = new GameResult(dealer, participants);
        ParticipantResult result = gameResult.getWinLoseResult().get(participant1);

        // then
        assertThat(result).isEqualTo(ParticipantResult.WIN);

    }

    @Test
    void 플레이어_모두_버스트인_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant("프리");
        Participant participant2 = new Participant("벡터");

        Participants participants = new Participants(List.of(participant1, participant2));

        participant1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        participant1.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        GameResult gameResult = new GameResult(dealer, participants);
        ParticipantResult result = gameResult.getWinLoseResult().get(participant1);
        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);

    }
}
