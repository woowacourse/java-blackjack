package domain.card;

import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardDeckTest {

    @Test
    @DisplayName("카드 한 장 뽑기 테스트")
    void hitCardTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, TWO)));

        // when
        Card card = cardDeck.hitCard();

        // then
        assertSoftly(softly -> {
            softly.assertThat(card.getShape()).isEqualTo(DIAMOND);
            softly.assertThat(card.getNumber()).isEqualTo(TWO);
        });
    }

    @Test
    @DisplayName("카드 추가 테스트")
    void addCardTest() {
        // given
        CardDeck cardDeck = new CardDeck(new ArrayList<>());
        Card card = new Card(DIAMOND, TWO);

        // when
        cardDeck.addCard(card);

        // then
        assertThat(cardDeck.getCards().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("카드 합계 테스트")
    @MethodSource("provideCardDeckForSumWithAce")
    void sumWithAceTest(CardDeck cardDeck, int expectedSum) {
        assertThat(cardDeck.sumWithAce()).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> provideCardDeckForSumWithAce() {
        return Stream.of(Arguments.of(
                new CardDeck(List.of(new Card(DIAMOND, ACE))), 11,
                new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE))), 12,
                new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE))), 13,
                new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE),
                        new Card(CLOVER, ACE))), 14,
                new CardDeck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE))), 21,
                new CardDeck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE), new Card(HEART, ACE))), 12,
                new CardDeck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, QUEEN), new Card(HEART, ACE))), 21,
                new CardDeck(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE),
                        new Card(SPADE, ACE), new Card(HEART, ACE), new Card(CLOVER, ACE))), 19,
                new CardDeck(List.of(new Card(SPADE, JACK), new Card(DIAMOND, JACK))), 20
        ));
    }

    @Test
    @DisplayName("히든 카드를 제외한 카드 반환 테스트")
    void getCardExceptHiddenTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)));

        // when
        Card card = cardDeck.getCardExceptHidden();

        // then
        assertSoftly(softly -> {
            softly.assertThat(card.getShape()).isEqualTo(DIAMOND);
            softly.assertThat(card.getNumber()).isEqualTo(ACE);
        });

    }
}
