package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.strategy.BlackjackDrawStrategy;
import domain.card.strategy.DrawStrategy;
import domain.fixture.BlackjackDeckTestFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    @Test
    void 트럼프_카드_덱을_생성한다(){
        //given
        DrawStrategy drawStrategy = new BlackjackDrawStrategy();

        // when & then
        assertThatCode(() -> new Deck(drawStrategy))
                .doesNotThrowAnyException();
    }

    @Test
    void 트럼프_카드를_덱에서_뽑을_수_있다() {
        // given
        List<TrumpCard> drawCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.EIGHT));
        Deck deck = BlackjackDeckTestFixture.createSequentialDeck(drawCards);

        //when
        TrumpCard drawCard = deck.drawCard();

        //then
        TrumpCard expected = new TrumpCard(Suit.CLOVER, CardValue.EIGHT);
        assertThat(drawCard).isEqualTo(expected);
    }

    @Test
    void 덱에서_카드가_없으면_예외가_발생한다() {
        // given
        Deck deck = BlackjackDeckTestFixture.createRandomDeck();
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        // when & then
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱이 비어있어 뽑을 수 없습니다.");
    }
}
