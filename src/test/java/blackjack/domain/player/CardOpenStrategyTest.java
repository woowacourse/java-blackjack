package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardOpenStrategyTest {
    private static List<Card> cards;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        cards.add(Cards.getInstance().draw());
        cards.add(Cards.getInstance().draw());
    }

    @DisplayName("한장만 오픈하는 전략패턴 테스트")
    @Test
    void OneCardOpenStrategy() {
        CardOpenStrategy oneCardOpenStrategy = new OneCardOpenStrategy();
        oneCardOpenStrategy.getCards(cards);
        assertThat(oneCardOpenStrategy.getCards(cards)).hasSize(1);
    }

    @DisplayName("모든 카드를 오픈하는 전략패턴 테스트")
    @Test
    void TwoCardOpenStrategy() {
        CardOpenStrategy oneCardOpenStrategy = new AllCardsOpenStrategy();
        oneCardOpenStrategy.getCards(cards);
        assertThat(oneCardOpenStrategy.getCards(cards)).hasSize(2);
        cards.add(Cards.getInstance().draw());
        assertThat(oneCardOpenStrategy.getCards(cards)).hasSize(3);
    }
}
