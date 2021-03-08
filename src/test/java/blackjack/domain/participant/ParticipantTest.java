package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class ParticipantTest {

    @DisplayName("양쪽 공백을 제외한 이름의 글자 수가 1글자 이상이 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"   ", " ", ""})
    void validateParticipantName(String name) {
        assertThatCode(() -> new Player(name, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 양쪽 공백을 제외한 1글자 이상이어야 합니다.");
    }

    @DisplayName("이름에 null이 들어오면 예외가 발생한다")
    @Test
    void validateParticipantNameWhenNull() {
        assertThatCode(() -> new Player(null, 100))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("게임 참가자는 1장의 카드를 뽑을 수 있다.")
    @Test
    void drawCard() {
        Participant participant = new Player("jason", 100);
        Card card = new Card(Symbol.EIGHT, Shape.CLOVER);

        participant.receiveCard(card);
        Set<Card> playerCards = participant.getCards();

        assertThat(playerCards).containsExactly(card);
    }

    @DisplayName("게임 참가자는 복수장의 카드를 뽑을 수 있다.")
    @Test
    void drawCards() {
        Participant participant = new Player("jason", 100);
        Card firstCard = new Card(Symbol.ACE, Shape.DIAMOND);
        Card secondCard = new Card(Symbol.FIVE, Shape.HEART);
        Cards cards = new Cards(Arrays.asList(firstCard, secondCard));

        participant.receiveCards(cards);
        Set<Card> playerCards = participant.getCards();

        assertThat(playerCards).containsExactly(firstCard, secondCard);
    }
}
