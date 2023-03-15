package blackjack.domain.card;

import static blackjack.domain.card.CardFixture.CLOVER_ACE;
import static blackjack.domain.card.CardFixture.DIAMOND_EIGHT;
import static blackjack.domain.card.CardFixture.DIAMOND_TEN;
import static blackjack.domain.card.CardFixture.HEART_EIGHT;
import static blackjack.domain.card.CardFixture.SPADE_ACE;
import static blackjack.domain.card.CardResponseFixture.CLOVER_ACE_RESPONSE;
import static blackjack.domain.card.CardResponseFixture.HEART_EIGHT_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPocketTest {

    private static final List<Card> cards = List.of(CLOVER_ACE, HEART_EIGHT);

    private CardPocket cardPocket;

    @BeforeEach
    void setCardPocket() {
        cardPocket = CardPocket.empty();
        cards.forEach(cardPocket::addCard);
    }

    @Test
    void 카드_포켓_안의_카드의_점수_계산() {
        assertThat(cardPocket.calculateScore())
                .isEqualTo(19);

    }

    @Test
    void 카드_포켓에_카드_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(SPADE_ACE);

        assertThat(cardPocket.calculateScore())
                .isEqualTo(20);

    }

    @Test
    void 카드_포켓에_카드_두번_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(DIAMOND_TEN);
        cardPocket.addCard(DIAMOND_EIGHT);

        assertThat(cardPocket.calculateScore())
                .isEqualTo(27);
    }

    @Test
    void 카드_포켓에서_카드_가져오는_기능_추가() {
        assertThat(cardPocket.getCards())
                .containsExactly(CLOVER_ACE_RESPONSE, HEART_EIGHT_RESPONSE);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE, TEN, true", "JACK, ACE, true", "ACE, ACE, false", "TEN, TEN, false"})
    void 블랙잭인_경우_2장으로_21점이_되는_경우_검증(
            final Symbol firstSymbol,
            final Symbol secondSymbol,
            final boolean expected) {
        final CardPocket cardPocket = CardPocket.empty();
        cardPocket.addCard(new Card(Shape.DIAMOND, firstSymbol));
        cardPocket.addCard(new Card(Shape.DIAMOND, secondSymbol));

        assertThat(cardPocket.isBlackJack())
                .isEqualTo(expected);
    }
}
