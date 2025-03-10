package domain;

import static domain.card.Number.ACE;
import static domain.card.Number.JACK;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import config.CardDeckFactory;
import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
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
    void hitCardsTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Dealer dealer = new Dealer();

        // when
        dealer.hitCard(cardDeck);
        // then
        assertThat(dealer.getHand().getCards().size()).isEqualTo(2);
    }


    @Test
    @DisplayName("카드 합계 테스트")
    void sumTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, TWO), new Card(SPADE, TWO), new Card(HEART, TWO)));
        Dealer dealer = new Dealer();
        dealer.hitCard(cardDeck);
        dealer.draw(cardDeck);

        // when-then
        assertThat(dealer.sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("딜러 추가 카드 뽑기 테스트")
    void checkIsUnderThresholdTest() {
        //given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer();
        dealer.hitCard(cardDeck);

        //when-then
        assertThat(dealer.isUnderThreshold()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("드로우 테스트")
    @MethodSource("provideCardDeckForDrawTest")
    void drawTest(CardDeck cardDeck, int size) {
        // given
        Dealer dealer = new Dealer();
        dealer.hitCard(cardDeck);

        // when
        dealer.draw(cardDeck);

        // then
        assertThat(dealer.getHand().getCards().size()).isEqualTo(size);
    }

    private static Stream<Arguments> provideCardDeckForDrawTest() {
        return Stream.of(Arguments.of(
                new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(DIAMOND, TWO))), 3,
                new CardDeck(List.of(new Card(CLOVER, JACK), new Card(SPADE, JACK), new Card(DIAMOND, JACK))), 2
        ));
    }

    @Test
    @DisplayName("히든 카드를 제외한 카드 반환 테스트")
    void getCardExceptHiddenTest(){
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));
        Dealer dealer = new Dealer();
        dealer.hitCard(cardDeck);

        // when
        Card card = dealer.getHandExceptHidden();

        // then
        assertSoftly(softly -> {
            softly.assertThat(card.getShape()).isEqualTo(DIAMOND);
            softly.assertThat(card.getNumber()).isEqualTo(ACE);
        });

    }
}
