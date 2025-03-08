package domain;

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
    void 플레이어가_카드를뽑으면_새로운플레이어를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago"), new TestCardProvider());
        Cards cardsOfDrago = new Cards(List.of(new Card(Symbol.CLOVER, Number.EIGHT), new Card(Symbol.HEART, Number.JACK)));
        Player player = new Player("drago", cardsOfDrago);

        Cards newCardsOfDrago = new Cards(List.of(new Card(Symbol.CLOVER, Number.EIGHT), new Card(Symbol.HEART, Number.JACK),
                new Card(Symbol.SPADE, Number.KING)));
        Player expected = new Player("drago", newCardsOfDrago);

        assertThat(gameManager.drawCard(player)).isEqualTo(expected);
    }

    @Test
    void 딜러가_카드를뽑으면_새로운딜러를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago"), new TestCardProvider());
        Cards cardsOfDealer = new Cards(List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.SPADE, Number.ACE)));
        Dealer dealer = new Dealer(cardsOfDealer);

        Cards newCardsOfDealer = new Cards(List.of(
                new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.SPADE, Number.ACE), new Card(Symbol.SPADE, Number.KING)));
        Dealer expected = new Dealer(newCardsOfDealer);

        assertThat(gameManager.drawCard(dealer)).isEqualTo(expected);
    }

    @Test
    void 최종_게임_결과를_반환한다() {
        GameManager gameManager = new GameManager(List.of("drago", "duei"), new TestCardProvider());
        Cards cardsOfDrago = new Cards(List.of(new Card(Symbol.CLOVER, Number.EIGHT), new Card(Symbol.HEART, Number.JACK)));
        Cards cardsOfDuei = new Cards(List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.SPADE, Number.ACE)));
        Map<Player, ResultStatus> result = gameManager.findGameResult();

        Map<Player, ResultStatus> expected = Map.of(
            new Player("drago", cardsOfDrago), ResultStatus.LOSE,
            new Player("duei", cardsOfDuei), ResultStatus.LOSE
        );
        assertThat(result).isEqualTo(expected);
    }
}
