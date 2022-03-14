package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantStatusTest {

    @Test
    @DisplayName("카드 상태가 버스트라면 버스트 상태로 리프레시될 수 있다.")
    void refreshStatusByBust() {
        final ParticipantStatus participantStatus = ParticipantStatus.RUNNING;
        final Cards cards = createCards(Card.of(SPADE, KING), Card.of(SPADE, QUEEN));
        cards.addCard(Card.of(SPADE, JACK));

        assertThat(participantStatus.refreshStatus(cards)).isEqualTo(ParticipantStatus.BUST);
    }

    @Test
    @DisplayName("카드 상태가 블랙잭이라면 블랙잭 상태로 리프레시될 수 있다.")
    void refreshStatusByBlackJack() {
        final ParticipantStatus participantStatus = ParticipantStatus.RUNNING;
        final Cards cards = createCards(Card.of(SPADE, KING), Card.of(SPADE, A));

        assertThat(participantStatus.refreshStatus(cards)).isEqualTo(ParticipantStatus.BLACKJACK);
    }
}
