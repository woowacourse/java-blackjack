package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class ParticipantCardTest {

    private Card card;
    private ParticipantCard participantCard;

    @BeforeAll
    void init() {
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        participantCard = ParticipantCard.create();
    }

    @Test
    @DisplayName("create()는 호출하면, ParticipantCard를 생성한다")
    void create_whenCall_thenSuccess() {
        assertThatCode(() -> ParticipantCard.create())
                .doesNotThrowAnyException();

        assertThat(ParticipantCard.create())
                .isExactlyInstanceOf(ParticipantCard.class);
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_whenSuccess() {
        // when
        participantCard.addCard(card);

        // then
        assertThat(participantCard)
                .extracting("cards", as(list(Card.class)))
                .hasSize(1);
    }

    @Test
    @DisplayName("getFirst()는 호출하면, 참가자의 첫 번째 카드를 조회한다")
    void getFirst_whenCall_thenReturnFirstCard() {
        // given
        participantCard.addCard(card);

        // when
        Card actual = participantCard.getFirstCard();

        // then
        assertThat(actual).isSameAs(card);
    }
}
