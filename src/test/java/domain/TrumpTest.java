package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Trump;
import blackjack.strategy.ShuffleStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrumpTest {

    private Trump trump;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = new NoShuffleStrategy();
        trump = new Trump(strategy);
    }

    @Test
    @DisplayName("트럼프 덱 생성 테스트")
    void 트럼프_덱_생성_테스트() {
        List<Suit> suits = Suit.all();
        List<Denomination> denominations = Denomination.all();

        List<String> expected = suits.stream()
                .flatMap(suit -> denominations.stream()
                        .map(denomination -> new Card(suit, denomination))
                        .map(Card::toString))
                .collect(Collectors.toCollection(ArrayList::new));

        List<String> actual = new ArrayList<>();
        for (int i = 0; i < 13 * 4; i++) {
            actual.add(trump.draw().toString());
        }

        assertThat(actual.reversed()).isEqualTo(expected);
    }
}
