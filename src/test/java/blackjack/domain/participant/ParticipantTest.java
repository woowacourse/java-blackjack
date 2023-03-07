package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ParticipantTest {

    Name name;
    List<Card> cards;

    @BeforeEach
    void initialData() {
        name = new Name("test");
        cards = new ArrayList<>();
    }

    @Test
    @DisplayName("플레이어 생성 테스트")
    void constructorPlayer() {
        assertThatNoException().isThrownBy(() -> new Participant(name, cards));
    }

    @Test
    @DisplayName("카드 한장을 가져오는지 테스트")
    void drawCardTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card = new Card(Shape.CLOVER, Letter.ACE);

        // when
        participant.draw(card);

        // then
        Assertions.assertThat(participant.getCardNames())
                .contains(card.getCardName());
    }

    @Test
    @DisplayName("카드의 총 합을 보여주는 테스트")
    void getTotalScoreTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        int expected = card1.getValue() + card2.getValue();
        participant.draw(card1);
        participant.draw(card2);

        // when
        int actual = participant.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어 이름 반환하는 테스트")
    void getNameTest() {
        Participant participant = new Participant(name, cards);
        assertThat(participant.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("플레이어의 카드 리스트를 반환하는 테스트")
    void getCardsTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        // when
        participant.draw(card1);
        participant.draw(card2);

        // then
        Assertions.assertThat(participant.getCards()).contains(card1, card2);
    }

    @Test
    @DisplayName("플레이어의 카드 이름 리스트를 반환하는 테스트 ")
    void getCardNamesTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card1 = new Card(Shape.CLOVER, Letter.ACE);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);

        // when
        participant.draw(card1);
        participant.draw(card2);

        // then
        Assertions.assertThat(participant.getCardNames()).contains(card1.getCardName(), card2.getCardName());
    }

    @Test
    @DisplayName("합이 21 초과인지 테스트")
    void isBustTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card1 = new Card(Shape.CLOVER, Letter.TWO);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        Card card3 = new Card(Shape.DIAMOND, Letter.QUEEN);

        // when
        participant.draw(card1);
        participant.draw(card2);
        participant.draw(card3);

        // then
        assertThat(participant.isBust()).isTrue();
    }

    @Test
    @DisplayName("합이 21 이하인지 테스트")
    void isNotBustTest() {
        // given
        Participant participant = new Participant(name, cards);
        Card card1 = new Card(Shape.CLOVER, Letter.TWO);
        Card card2 = new Card(Shape.DIAMOND, Letter.JACK);
        Card card3 = new Card(Shape.DIAMOND, Letter.NINE);

        // when
        participant.draw(card1);
        participant.draw(card2);
        participant.draw(card3);

        // then
        assertThat(participant.isBust()).isFalse();
    }
}
