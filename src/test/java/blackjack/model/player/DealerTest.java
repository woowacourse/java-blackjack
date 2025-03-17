package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.game.BetAmount;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러를_생성한다() {
        // given
        Dealer dealer = new Dealer();

        // when & then
        assertThat(dealer).isNotNull();
    }

    @Test
    void 딜러에게_카드를_한장_준다() {
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        assertThat(dealer.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러의_수익을_계산한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant("한스", new BetAmount(20000));
        Participant participant2 = new Participant("백터", new BetAmount(10000));
        Participants participants = new Participants(List.of(participant1, participant2));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_10));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_10));
        dealer.putCard(new Card(CardShape.HEART, CardType.ACE));

        participant1.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_10));

        participant2.putCard(new Card(CardShape.CLOVER, CardType.KING));
        participant2.putCard(new Card(CardShape.CLOVER, CardType.ACE));

        // when
        double dealerAmount = dealer.calculateProfitAmount(participants);
        double participant1Amount = participant1.calculateProfitAmount(participant1.duelWith(dealer));
        double participant2Amount = participant2.calculateProfitAmount(participant2.duelWith(dealer));

        // then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(dealerAmount).isEqualTo(5000);
        softly.assertThat(participant1Amount).isEqualTo(-20000);
        softly.assertThat(participant2Amount).isEqualTo(15000);
        softly.assertAll();
    }

    @Test
    void 딜러는_게임을_시작하면_카드_한장만_공개한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_10));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_10));
        dealer.putCard(new Card(CardShape.HEART, CardType.ACE));

        // when
        List<Card> cards = dealer.getInitialCards();

        // then
        assertThat(cards.size()).isEqualTo(1);
        assertThat(cards.get(0).getShape()).isEqualTo(CardShape.HEART);
        assertThat(cards.get(0).getCardType()).isEqualTo(CardType.NORMAL_10);

    }
}
