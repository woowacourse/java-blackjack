package domain;

import domain.card.Card;
import domain.card.CardProvider;
import domain.card.Number;
import domain.card.Symbol;
import domain.dto.PlayerInfo;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 매니저 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameManagerTest {

    private class TestCardProvider implements CardProvider {

        private final Deque<Card> cardQueue = new ArrayDeque<>(List.of(
            new Card(Symbol.CLOVER, domain.card.Number.EIGHT),
            new Card(Symbol.HEART, domain.card.Number.JACK),
            new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
            new Card(Symbol.SPADE, domain.card.Number.ACE),
            new Card(Symbol.SPADE, domain.card.Number.KING),
            new Card(Symbol.CLOVER, domain.card.Number.ACE)
        ));

        @Override
        public List<Card> provideCards(int size) {
            List<Card> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                result.add(cardQueue.poll());
            }
            return result;
        }
    }

    @Test
    void 초기_딜러_카드_정보를_반환한다() {
        GameManager gameManager = new GameManager(
            List.of(new PlayerInfo("drago", 2000), new PlayerInfo("duei", 1000)),
            new TestCardProvider());
        List<Card> cardsOfDealer = List.of(new Card(Symbol.SPADE, domain.card.Number.KING), new Card(Symbol.CLOVER, domain.card.Number.ACE));

        Dealer result = gameManager.findDealer();

        Dealer expected = new Dealer(cardsOfDealer);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 플레이어별_수익률을_반환한다() {
        GameManager gameManager = new GameManager(
            List.of(new PlayerInfo("drago", 2000), new PlayerInfo("duei", 1000)),
            new TestCardProvider());

        Map<Player, Integer> incomes = gameManager.calculateIncomes();

        Map<Player, Integer> expected = Map.of(
            new Player("drago", List.of(new Card(Symbol.CLOVER, domain.card.Number.EIGHT), new Card(Symbol.HEART, domain.card.Number.JACK)), 2000),
            -2000,
            new Player("duei", List.of(new Card(Symbol.DIAMOND, domain.card.Number.EIGHT), new Card(Symbol.SPADE, Number.ACE)), 1000),
            -1000
        );

        assertThat(incomes).isEqualTo(expected);
    }
}
