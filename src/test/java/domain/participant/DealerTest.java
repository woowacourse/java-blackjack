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

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsToParticipantTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        //when-then
        assertThat(dealer.hitCard()).isInstanceOf(Card.class);

    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardsTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer(cardDeck);
        // when-then
        assertDoesNotThrow(dealer::addCards);
    }


    @Test
    @DisplayName("카드 합계 테스트")
    void calculateSumTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        //when-then
        assertThat(dealer.calculateSum()).isEqualTo(12);
    }

    @Test
    @DisplayName("딜러 추가 카드 뽑기 테스트")
    void checkIsUnderThresholdTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        //when-then
        assertThat(dealer.isUnderThreshold()).isTrue();
    }

    @Test
    @DisplayName("딜러 추가 카드 뽑기 테스트 (false) 리턴")
    void checkIsNotUnderThresholdTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, JACK), new Card(SPADE, QUEEN)));
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        //when-then
        assertThat(dealer.isUnderThreshold()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("드로우 테스트")
    @MethodSource("provideCardDeckForDrawTest")
    void drawTest(CardDeck cardDeck) {
        //given
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        //when-then
        assertDoesNotThrow(dealer::draw);
    }

    private static Stream<Arguments> provideCardDeckForDrawTest() {
        return Stream.of(Arguments.of(
                new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, TWO))),
                new CardDeck(List.of(new Card(CLOVER, JACK), new Card(SPADE, JACK), new Card(DIAMOND, JACK))),
                new CardDeck(List.of(new Card(CLOVER, SEVEN), new Card(SPADE, JACK), new Card(DIAMOND, SEVEN)))
        ));
    }

    @Test
    @DisplayName("딜러 드로우 안 터지는 테스트")
    void notDrawTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(CLOVER, SEVEN), new Card(SPADE, JACK), new Card(DIAMOND, SEVEN)));
        Dealer dealer = new Dealer(cardDeck);
        dealer.addCards();
        dealer.addCards();

        assertDoesNotThrow(dealer::draw);
    }
}
