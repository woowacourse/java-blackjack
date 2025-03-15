package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void 카드_덱에서_카드_두_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Participant participant = new Participant() {
        };

        //when
        participant.drawCardWhenStart(cardDeck);

        //then
        assertThat(participant.getCards()).hasSize(2);
    }

    @Test
    void 카드_덱에서_카드_한_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Participant participant = new Participant() {
        };

        //when
        participant.drawCard(cardDeck);

        //then
        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    void 보유한_카드의_합계를_계산한다() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when
        int totalNumber = participant.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(22);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_11로_판단() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when
        int totalNumber = participant.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(21);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_ace를_1로_판단() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TWO));

        //when
        int totalNumber = participant.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(13);
    }

    @Test
    void 보유한_카드의_합계를_계산한다_21초과해도_ace를_1로_판단() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when
        int totalNumber = participant.calculateTotalCardNumber();

        //then
        assertThat(totalNumber).isEqualTo(23);
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21초과_케이스() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(participant.isOverBurstBound()).isTrue();
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다_21이하_케이스() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.NINE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(participant.isOverBurstBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_11로_처리() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));

        //when & then
        assertThat(participant.isOverBurstBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리하면_통과() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));

        //when & then
        assertThat(participant.isOverBurstBound()).isFalse();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace를_1로_처리해도_버스트() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when & then
        assertThat(participant.isOverBurstBound()).isTrue();
    }

    @Test
    void ace를_가진_경우_합계가_21이_넘어가는지_판정한다_ace_4개_케이스() {
        //given
        Participant participant = new Participant() {
        };
        List<Card> drawnCards = participant.getCards();
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.ACE));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when & then
        assertThat(participant.isOverBurstBound()).isFalse();
    }
}
