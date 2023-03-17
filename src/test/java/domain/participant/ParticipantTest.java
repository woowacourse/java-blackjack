package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.card.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    private Card card;
    private Participant participant;

    @BeforeEach
    void init() {
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        participant = Participant.create("pobi");
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        participant.addCard(card);
        final List<Card> cards = participant.getHand();

        // then
        assertThat(cards)
                .hasSize(1);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBustCard")
    @ParameterizedTest(name = "isBust()는 호출하면 버스트인지 확인한다")
    void isBust_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(participant::addCard);

        // when
        final boolean actual = participant.isBust();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBlackJackCard")
    @ParameterizedTest(name = "isBlackJack()은 호출하면 블랙잭인지 확인한다")
    void isBlackJack_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(participant::addCard);

        // when
        final boolean actual = participant.isBlackJack();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeCards")
    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(participant::addCard);

        // when
        final Score score = participant.calculateScore();

        // then
        assertThat(score)
                .isEqualTo(Score.create(expected));
    }
}
