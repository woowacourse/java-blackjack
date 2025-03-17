package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.game.ParticipantResult;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 참여자의_이름이_2글자_이상_5글자가_아니면_예외가_발생한다() {
        // given

        // when & then
        assertThatThrownBy(() -> new Participant("한스한스한스"));
    }

    @Test
    void 참여자에게_카드를_한장_준다() {
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        assertThat(participant.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 플레이어가_버스트가_아니고_참가자가_패배인_경우() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_2));

        // when
        ParticipantResult result = participant.duelWith(dealer);

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
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);

    }

    @Test
    void 참가자가_버스트가_아니고_딜러가_버스트가_아닌_경우_점수가_같으면_무승부다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));

        // when
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.DRAW);

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
        ParticipantResult result = participant.duelWith(dealer);

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
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 참여자가_블랙잭인_경우_참여자는_블랙잭으로_승리한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.ACE));
        participant.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.BLACKJACK);
    }

    @Test
    void 딜러와_플레이어_모두_블랙잭인_경우_무승부이다(){
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.ACE));
        participant.putCard(new Card(CardShape.CLOVER, CardType.KING));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.ACE));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.DRAW);
    }

    @Test
    void 세_장의_카드로_21인_경우_블랙잭이_아니다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_10));
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_10));
        participant.putCard(new Card(CardShape.CLOVER, CardType.ACE));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.ACE));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // when
        ParticipantResult result = participant.duelWith(dealer);

        // then
        assertThat(result).isEqualTo(ParticipantResult.LOSE);
    }

    @Test
    void 플레이어는_게임이_시작하면_카드를_모두_공개한다() {
        // given
        Participant participant = new Participant("프리");
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_10));
        participant.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_10));
        participant.putCard(new Card(CardShape.CLOVER, CardType.ACE));

        // when
        List<Card> cards = participant.getInitialCards();

        // then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cards.size()).isEqualTo(3);
        softly.assertThat(cards.get(0).getCardType()).isEqualTo(CardType.NORMAL_10);
        softly.assertThat(cards.get(1).getCardType()).isEqualTo(CardType.NORMAL_10);
        softly.assertThat(cards.get(2).getCardType()).isEqualTo(CardType.ACE);
        softly.assertAll();
    }
}
