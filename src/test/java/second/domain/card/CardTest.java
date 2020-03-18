package second.domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void 정적팩토리테스트() {
        assertThat(Card.of(Rank.ACE, Suit.HEART)).isInstanceOf(Card.class);
    }

    @Test
    void isAce() {
        final Card cloverAceCard = Card.of(Rank.ACE, Suit.CLOVER);

        final boolean trueResult = cloverAceCard.isAce();

        assertThat(trueResult).isTrue();

        final Card cloverKingCard = Card.of(Rank.K, Suit.CLOVER);

        final boolean falseResult = cloverKingCard.isAce();

        assertThat(falseResult).isFalse();
    }

    @Test
    void extractScore() {
        final Card aceCard = Card.of(Rank.ACE, Suit.CLOVER);

        final int result = aceCard.extractScore();

        assertThat(result).isEqualTo(1);
    }
}
