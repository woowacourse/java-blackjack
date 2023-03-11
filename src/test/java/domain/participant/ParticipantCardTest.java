package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantCardTest {

    private ParticipantCard participantCard;

    @BeforeEach
    void init() {
        participantCard = ParticipantCard.create();
    }

    @Test
    @DisplayName("create()는 호출하면 ParticipantCard를 생성한다")
    void create_whenCall_thenSuccess() {
        final ParticipantCard participantCard = assertDoesNotThrow(ParticipantCard::create);

        assertThat(participantCard)
                .isExactlyInstanceOf(ParticipantCard.class);
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_whenSuccess() {
        // when
        final Card card = Card.of(Shape.HEART, Denomination.ACE);
        participantCard.addCard(card);

        // then
        final List<Card> cards = participantCard.getCards();
        assertThat(cards)
                .hasSize(1);
    }

    @Test
    @DisplayName("getFirst()는 호출하면 참가자의 첫 번째 카드를 조회한다")
    void getFirst_whenCall_thenReturnFirstCard() {
        // given
        final Card card = Card.of(Shape.HEART, Denomination.ACE);
        participantCard.addCard(card);

        // when
        final Card actual = participantCard.getFirstCard();

        // then
        assertThat(actual)
                .isSameAs(card);
    }

    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    @MethodSource(value = "domain.helper.ParticipantArguments#makeCards")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expectedScore) {
        // given
        cards.forEach(participantCard::addCard);

        // when
        final ParticipantScore actual = participantCard.calculateScore();

        // then
        final ParticipantScore expected = ParticipantScore.scoreOf(expectedScore);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "checkBust()는 호출하면 버스트인지 여부를 반환한다")
    @MethodSource(value = "domain.helper.ParticipantArguments#makeBustCard")
    void checkBust_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(participantCard::addCard);

        // when
        final boolean actual = participantCard.checkBust();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "checkBlackJack()은 호출하면 블랙잭인지 여부를 반환한다")
    @MethodSource(value = "domain.helper.ParticipantArguments#makeBlackJackCard")
    void checkBlackJack_whenCall_thenReturnIsBlackJack(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(participantCard::addCard);

        // when
        final boolean actual = participantCard.checkBlackJack();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
