package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 매니저 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GameManagerTest {

    private class TestCardProvider implements CardProvider{

        private final Deque<Card> cardQueue = new ArrayDeque<>(List.of(
            new Card(Symbol.CLOVER, Number.EIGHT),
            new Card(Symbol.HEART, Number.JACK),
            new Card(Symbol.DIAMOND, Number.EIGHT),
            new Card(Symbol.SPADE, Number.ACE),
            new Card(Symbol.SPADE, Number.KING),
            new Card(Symbol.CLOVER, Number.ACE)
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
        GameManager gameManager = new GameManager(List.of("drago", "duei"), new TestCardProvider());
        Cards cardsOfDealer = new Cards(List.of(new Card(Symbol.SPADE, Number.KING), new Card(Symbol.CLOVER, Number.ACE)));

        Dealer result = gameManager.findDealer();

        Dealer expected = new Dealer(cardsOfDealer);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 플레이어_카드_정보를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago", "duei"), new TestCardProvider());
        Cards cardsOfDrago = new Cards(List.of(new Card(Symbol.CLOVER, Number.EIGHT), new Card(Symbol.HEART, Number.JACK)));

        Player result = gameManager.findPlayer("drago");

        Player expected = new Player(new Name("drago"), cardsOfDrago);
        assertThat(result).isEqualTo(expected);
    }
}
