package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.CardDto;
import blackjack.model.card.Hands;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("카드 덱에서 추가 1장 더 가져오기")
    void pickAdditionalTest() {
        // given
        Participant participant = new TestParticipant();
        CardDeck cardDeck = CardDeck.of(mustPickAce);

        // when
        participant.pickAdditionalCard(cardDeck);

        // then
        assertThat(participant.getAllCards().size()).isEqualTo(1);
        assertThat(participant.getAllCards().getFirst().rank()).isEqualTo(Rank.ACE);
    }

    @Test
    @DisplayName("버스트 테스트")
    void isBustTest() {
        // given
        CardDeck tenCardDeck = CardDeck.of(mustPickTen);
        Participant participant1 = new TestParticipant();
        Participant participant2 = new TestParticipant();

        // when
        participant1.pickAdditionalCard(tenCardDeck);
        participant1.pickAdditionalCard(tenCardDeck);
        participant1.pickAdditionalCard(tenCardDeck); // pick 30

        participant2.pickAdditionalCard(tenCardDeck); // pick 10

        // then
        assertThat(participant1.isBust()).isTrue();
        assertThat(participant2.isBust()).isFalse();
    }

    PickStrategy mustPickTen = cards -> Card.of(Rank.TEN, Suit.CLOVER);
    PickStrategy mustPickAce = cards -> Card.of(Rank.ACE, Suit.CLOVER);

    static class TestParticipant extends Participant {

        public TestParticipant() {
            super("test", Hands.empty());
        }

        @Override
        public List<CardDto> getInitCards() {
            return List.of();
        }
    }
}
