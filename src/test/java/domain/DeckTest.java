package domain;

import domain.constant.Rank;
import domain.constant.Suit;
import domain.shuffle.RandomShuffle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    void 덱의_초기_카드는_52장이며_무늬순_숫자순으로_정렬되어있다() {
        Deck deck = new Deck();

        // 덱 초기 카드 수 테스트
        assertThat(deck.size()).isEqualTo(52);

        // 덱 초기 카드 정렬 테스트
        List<Card> actualCards = getRemainingCards(deck);
        List<Card> expectedCards = createExpectedCards();

        assertThat(actualCards).containsExactlyElementsOf(expectedCards);
    }

    @Test
    void 카드를_섞으면_순서가_바뀐다() {
        Deck deck = new Deck();
        List<Card> initialCards = getRemainingCards(new Deck());

        deck.shuffle(Collections::reverse);
        List<Card> shuffledCards = getRemainingCards(deck);

        assertThat(shuffledCards).containsExactlyInAnyOrderElementsOf(initialCards);
        assertThat(shuffledCards).isNotEqualTo(initialCards);
    }

    @Test
    void 초기_덱에서_카드를_뽑으면_첫_번째_카드인_A스페이드가_나온다() {
        Deck deck = new Deck();

        Card drawCard = deck.draw();

        assertThat(drawCard).isEqualTo(new Card(Rank.ACE, Suit.SPADE));
    }

    @Test
    void 덱에서_카드를_뽑으면_덱에_남아있는_카드의_숫자가_한_장_줄어든다() {
        Deck deck = new Deck();
        int beforeSize = deck.size();

        deck.draw();
        int afterSize = deck.size();
        int result = beforeSize - afterSize;

        assertThat(result).isEqualTo(1);
    }

    @Test
    void 덱에서_카드를_뽑으면_뽑은_카드는_덱에서_사라진다() {
        Deck deck = new Deck();

        Card drawnCard = deck.draw();
        List<Card> remainingCards = getRemainingCards(deck);

        assertThat(remainingCards).doesNotContain(drawnCard);
    }

    @Test
    void 덱에_남아있는_카드가_없으면_예외가_발생한다() {
        Deck deck = new Deck();

        getRemainingCards(deck);

        assertThatThrownBy(deck::draw).isInstanceOf(IllegalArgumentException.class);
    }

    private List<Card> getRemainingCards(Deck deck) {
        List<Card> result = new ArrayList<>();
        int deckSize = deck.size();
        for (int i = 0; i < deckSize; i++) {
            result.add(deck.draw());
        }
        return result;
    }

    private List<Card> createExpectedCards() {
        List<Card> expectedDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                expectedDeck.add(new Card(rank, suit));
            }
        }
        return expectedDeck;
    }
}
