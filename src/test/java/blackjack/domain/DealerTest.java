package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.util.FixedDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class DealerTest {

    @Test
    void 딜러를_생성한다() {
        Dealer dealer = Dealer.create();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Dealer dealer = Dealer.create();
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, Shape.DIAMOND)
        ));

        dealer.initialDraw(deck);

        assertThat(dealer.getCardLetters()).containsExactly("A다이아몬드");
    }
}
