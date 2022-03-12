package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @DisplayName("카드 덱은 52장의 카드(4종류 x 13랭크)로 구성된다.")
    @Test
    void init_cardDeckComposition() {
        List<Card> actual = new ArrayList<>();
        List<Card> expected = getAllCardsOfEachRankAndSymbol();

        for (int i = 0; i < 52; i++) {
            actual.add(cardDeck.pop());
        }

        assertThat(actual).containsAll(expected);
        assertThat(new HashSet<>(actual)).containsAll(new HashSet<>(expected));
        assertThat(new HashSet<>(actual).size()).isEqualTo(52);
    }


    @DisplayName("pop 메서드는 서로 중복되지 않는 카드를 카드덱에서 뽑아온다.")
    @Test
    void pop_eachCardIsUnique() {
        Card card1 = cardDeck.pop();
        Card card2 = cardDeck.pop();
        Card card3 = cardDeck.pop();

        int originalSize = List.of(card1, card2, card3).size();
        int noDuplicateSize = Set.of(card1, card2, card3).size();

        assertThat(originalSize).isEqualTo(noDuplicateSize);
    }

    @DisplayName("pop 메서드는 52회까지 실행할 수 있으며, 53번째에서는 예외가 발생한다.")
    @Test
    void pop_cardDeckConsistsOf52Cards() {
        for (int i = 0; i < 52; i++) {
            Assertions.assertDoesNotThrow(() -> cardDeck.pop());
        }

        assertThatThrownBy(() -> cardDeck.pop())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다!");
    }

    private List<Card> getAllCardsOfEachRankAndSymbol() {
        List<Card> expected = new ArrayList<>();
        Arrays.stream(CardRank.values())
                .map(this::getAllSymbolsOf)
                .forEach(expected::addAll);
        return expected;
    }

    private List<Card> getAllSymbolsOf(CardRank rank) {
        return Arrays.stream(CardSymbol.values())
                .map((symbol -> Card.of(rank, symbol)))
                .collect(Collectors.toList());
    }
}
