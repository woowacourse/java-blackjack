package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGeneratorImpl;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Participant> participants) {
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());
        Dealer dealer = new Dealer(initCards);
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());
        return Stream.of(
                List.of(
                        new Participant(initCards, "pobi"),
                        new Participant(initCards, "corinne")
                ),
                List.of(
                        new Participant(initCards, "1"),
                        new Participant(initCards, "2"),
                        new Participant(initCards, "3"),
                        new Participant(initCards, "4"),
                        new Participant(initCards, "5"),
                        new Participant(initCards, "6"),
                        new Participant(initCards, "7"),
                        new Participant(initCards, "8")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("participantListByFail")
    @DisplayName("참가자는 2~8명 사이이다. (실패)")
    void checkParticipantNumber(List<Participant> participants) {
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());
        Dealer dealer = new Dealer(initCards);

        Assertions.assertThatThrownBy(() -> new Players(participants, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자의 수는 2~8명 입니다.");
    }

    private static Stream<List<Player>> participantListByFail() {
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());
        return Stream.of(
                null,
                List.of(
                        new Participant(initCards, "pobi")
                ),
                List.of(
                        new Participant(initCards, "1"),
                        new Participant(initCards, "2"),
                        new Participant(initCards, "3"),
                        new Participant(initCards, "4"),
                        new Participant(initCards, "5"),
                        new Participant(initCards, "6"),
                        new Participant(initCards, "7"),
                        new Participant(initCards, "8"),
                        new Participant(initCards, "9")
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Deck deck = new Deck(new DeckGeneratorImpl());
        List<Card> initCards = new ArrayList<>();
        initCards.add(deck.draw());
        initCards.add(deck.draw());
        Dealer dealer = new Dealer(initCards);

        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant(initCards, "pobi"),
                        new Participant(initCards, "pobi")
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }
}
