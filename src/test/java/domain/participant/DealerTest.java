package domain.participant;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.DeckFactory;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {
    @Test
    @DisplayName("카드 추가 테스트")
    void addCardsTest() {
        // given
        DeckFactory deckFactory = new DeckFactory();
        Deck deck = deckFactory.create();
        Hand hand = new Hand(List.of(new Card(DIAMOND, TWO)));
        Dealer dealer = new Dealer(hand);
        //when-then
        assertDoesNotThrow(() -> dealer.addCard(deck.hitCard()));
    }


    @Test
    @DisplayName("카드 합계 테스트")
    void calculateSumTest() {
        //given
        Deck deck = new Deck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        //when-then
        assertThat(dealer.calculateSum()).isEqualTo(12);
    }

    @Test
    @DisplayName("딜러 추가 카드 뽑기 테스트")
    void checkIsUnderThresholdTest() {
        //given
        Deck deck = new Deck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        //when-then
        assertThat(dealer.isUnderThreshold()).isTrue();
    }

    @Test
    @DisplayName("딜러 추가 카드 뽑기 테스트 (false) 리턴")
    void checkIsNotUnderThresholdTest() {
        //given
        Deck deck = new Deck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, QUEEN)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        //when-then
        assertThat(dealer.isUnderThreshold()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("드로우 테스트")
    @MethodSource("provideCardDeckForDrawTest")
    void drawTest(Deck deck) {
        //given
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        //when-then
        assertDoesNotThrow(() -> dealer.draw(deck.hitCard()));
    }

    private static Stream<Arguments> provideCardDeckForDrawTest() {
        return Stream.of(
                Arguments.of(new Deck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, TWO))),
                Arguments.of(new Deck(List.of(new Card(CLOVER, JACK), new Card(SPADE, JACK), new Card(DIAMOND, JACK)))),
                Arguments.of(new Deck(List.of(new Card(CLOVER, SEVEN), new Card(SPADE, JACK), new Card(DIAMOND, SEVEN))))
        ));
    }

    @Test
    @DisplayName("딜러 드로우 안 터지는 테스트")
    void notDrawTest() {
        // given
        Deck deck = new Deck(List.of(new Card(CLOVER, SEVEN), new Card(SPADE, JACK), new Card(DIAMOND, SEVEN)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        assertDoesNotThrow(() -> dealer.draw(deck.hitCard()));
    }

    @Test
    @DisplayName("딜러 초기 카드 오픈 테스트")
    void openInitialCardsTest() {
        Deck deck = new Deck(List.of(new Card(CLOVER, SEVEN), new Card(SPADE, JACK), new Card(DIAMOND, SEVEN)));
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());

        assertThat(dealer.openInitialCards().getFirst()).isEqualTo(new Card(CLOVER, SEVEN));
    }
}
