package blackjack.domain.card;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.CardDeck.NUMBER_OF_FIRST_CARDS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {

    private static Stream<Arguments> createParticipant() {
        return Stream.of(
                Arguments.of(new Dealer()),
                Arguments.of(new Player("포비")));
    }

    @DisplayName("52장의 카드 한 벌을 생성하는지 확인")
    @Test
    void test1() {
        CardDeck cardDeck = new CardDeck();

        int actualCardDeckSize = cardDeck.size();

        assertThat(actualCardDeckSize).isEqualTo(52);
    }

    @DisplayName("카드덱이 첫 카드를 2장 전달하는지 확인")
    @ParameterizedTest
    @MethodSource("createParticipant")
    void test2(Participant participant) {
        CardDeck cardDeck = new CardDeck();
        int originalSize = cardDeck.size();

        cardDeck.dealFirstCards(participant);

        int actualDeckSize = cardDeck.size();
        int actualParticipantCardsSize = participant.getCards().size();

        assertThat(actualDeckSize).isEqualTo(originalSize - NUMBER_OF_FIRST_CARDS);
        assertThat(actualParticipantCardsSize).isEqualTo(NUMBER_OF_FIRST_CARDS);
    }

    @DisplayName("추가 카드를 하나씩 전달하는지 확인")
    @ParameterizedTest
    @MethodSource("createParticipant")
    void test3(Participant participant) {
        CardDeck cardDeck = new CardDeck();
        int originalSize = cardDeck.size();

        cardDeck.dealAdditionalCard(participant);

        int actualDeckSize = cardDeck.size();
        int actualParticipantCardsSize = participant.getCards().size();

        assertThat(actualDeckSize).isEqualTo(originalSize - 1);
        assertThat(actualParticipantCardsSize).isEqualTo(1);
    }
}
