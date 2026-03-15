package domain.participant;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Help;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Shape;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ParticipantCardsTest {

    @Test
    void 에이스가_없으면_카드_숫자의_합을_반환한다() {
        Help help1 = new Help(Shape.SPADE, domain.card.Number.EIGHT);
        Help help2 = new Help(Shape.HEART, domain.card.Number.EIGHT);

        ParticipantCards testParticipantCards = new ParticipantCards(new Cards(new ArrayList<>()));

        testParticipantCards.addCard(help1);
        testParticipantCards.addCard(help2);

        assertEquals(16, testParticipantCards.calculateScore());
    }

    @Test
    void 에이스가_있으면_상황에_맞는_최대_합을_반환한다() {
        Help help1 = new Help(Shape.SPADE, domain.card.Number.ACE);
        Help help2 = new Help(Shape.HEART, domain.card.Number.FIVE);
        Help help3 = new Help(Shape.CLUB, Number.EIGHT);

        ParticipantCards testParticipantCards = new ParticipantCards(new Cards(new ArrayList<>()));

        testParticipantCards.addCard(help1);
        testParticipantCards.addCard(help2);
        testParticipantCards.addCard(help3);

        assertEquals(14, testParticipantCards.calculateScore());
    }
}
