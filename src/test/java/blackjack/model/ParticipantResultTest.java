package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ParticipantResultTest {

    @Test
    void 플레이어가_버스트가_아니고_참가자가_패배인_경우() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_2));

        // when
        ParticipantResult result = ParticipantResult.of(dealer, participant);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 참가자가_버스트고_딜러가_버스트가_아닌_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        participant.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        participant.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = ParticipantResult.of(dealer, participant);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);

    }

    @Test
    void 딜러가_버스트고_참가자가_버스트가_아닌_경우_참가자승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = ParticipantResult.of(dealer, participant);

        // then
        assertThat(result).isEqualTo(ParticipantResult.WIN);

    }

    @Test
    void 플레이어_모두_버스트인_경우_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));
        participant.putCard(new Card(CardShape.CLOVER, CardType.JACK));
        participant.putCard(new Card(CardShape.CLOVER, CardType.QUEEN));
        participant.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = ParticipantResult.of(dealer, participant);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);

    }
}
