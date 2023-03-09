package domain.card;

import domain.fixture.CardAreaFixture;
import domain.player.Dealer;
import domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static domain.card.CardValue.*;
import static domain.fixture.CardDeckFixture.cardDeck;
import static domain.fixture.GamblerFixture.말랑;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardDeck 은")
class CardDeckTest {

    @Test
    void 카드를_종류별로_값_별로_한장씩_모두_가진_채로_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.shuffledFullCardDeck(new RandomCardShuffler());

        // when
        final Map<CardShape, List<Card>> result = cardDeck.cards()
                .stream()
                .collect(groupingBy(Card::cardShape));

        // then
        for (final CardShape cardShape : result.keySet()) {
            final Set<Card> cards = new HashSet<>(result.get(cardShape));

            assertEquals(cards.size(), values().length);
        }

        assertEquals(result.size(), CardShape.values().length);
    }

    @Test
    void 참가자에게_카드를_뽑아줄_수_있다() {
        // given
        final Gambler 말랑 = 말랑(CardAreaFixture.withTwoCard(TEN, TWO));
        final Dealer 딜러 = new Dealer(CardAreaFixture.withTwoCard(EIGHT, JACK));
        final CardDeck cardDeck = cardDeck(JACK, JACK);

        // when
        cardDeck.drawTo(말랑);
        cardDeck.drawTo(딜러);

        // then
        assertThat(cardDeck.cards().size()).isEqualTo(0);
    }
}
