package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 카드를_한_장_받는다() {
        // given
        Participant participant = Player.of("name", Money.of(1000));

        // when
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));

        // then
        assertThat(participant.getCardCount()).isEqualTo(1);
    }

    @Test
    void 참여자의_카드_점수를_반환한다() {
        // given
        Participant participant = Player.of("name", Money.of(1000));
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));
        participant.receive(Card.of(TrumpNumber.SIX, TrumpShape.DIAMOND));
        participant.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        int score = participant.getScore();

        // then
        assertThat(score).isEqualTo(18);
    }

    @Test
    void 카드_개수를_반환한다() {
        // given
        Participant participant = Player.of("name", Money.of(1000));
        participant.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));
        participant.receive(Card.of(TrumpNumber.SIX, TrumpShape.DIAMOND));
        participant.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.SPADE));

        // when
        int cardCount = participant.getCardCount();

        // then
        assertThat(cardCount).isEqualTo(3);
    }

    @Test
    void 금액을_증가시킨다() {
        // given
        Participant participant = Player.of("name", Money.of(100)); // 이름, 베팅 금액

        // when
        participant.increaseAmount(2000);

        // then
        assertThat(participant.getTotalWinnings()).isEqualTo(2000);
    }

    @Test
    void 금액을_감소시킨다() {
        // given
        Participant participant = Player.of("name", Money.of(100)); // 이름, 베팅 금액

        // when
        participant.decreaseAmount(2000);

        // then
        assertThat(participant.getTotalWinnings()).isEqualTo(-2000);
    }

    @Test
    void 참가자가_버스트인지_판단한다() {
        // given
        Participant bustParticipant = Player.of("name1", Money.of(100)); // 이름, 베팅 금액
        bustParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        bustParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        bustParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));

        Participant notBustParticipant = Player.of("name2", Money.of(100)); // 이름, 베팅 금액
        notBustParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        notBustParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));

        // when
        boolean bustParticipantResult = bustParticipant.isBust();
        boolean notBustParticipantResult = notBustParticipant.isBust();

        // then
        Assertions.assertAll(() -> {
            assertThat(bustParticipantResult).isTrue();
            assertThat(notBustParticipantResult).isFalse();
        });
    }

    @Test
    void 참가자가_블랙잭인지_판단한다() {
        // given
        Participant blackjackParticipant = Player.of("name1", Money.of(100)); // 이름, 베팅 금액
        blackjackParticipant.receive(Card.of(TrumpNumber.QUEEN, TrumpShape.CLUB));
        blackjackParticipant.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));

        Participant notBlackjackParticipant = Player.of("name2", Money.of(100)); // 이름, 베팅 금액
        notBlackjackParticipant.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        notBlackjackParticipant.receive(Card.of(TrumpNumber.JACK, TrumpShape.CLUB));
        notBlackjackParticipant.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));

        // when
        boolean blackjackParticipantResult = blackjackParticipant.isBlackjack();
        boolean notBlackjackParticipantResult = notBlackjackParticipant.isBlackjack();

        // then
        Assertions.assertAll(() -> {
            assertThat(blackjackParticipantResult).isTrue();
            assertThat(notBlackjackParticipantResult).isFalse();
        });
    }
}