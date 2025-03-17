package domain.user;

import domain.CardNumber;
import domain.CardShape;
import domain.GameManager;
import domain.TrumpCard;
import domain.TrumpCardManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private final List<Long> playersBettingMoney = List.of(300000L);

    private final List<TrumpCard> cardDeck = List.of(
            new TrumpCard(CardShape.CLOVER, CardNumber.J),
            new TrumpCard(CardShape.CLOVER, CardNumber.SEVEN),
            new TrumpCard(CardShape.CLOVER, CardNumber.FOUR),
            new TrumpCard(CardShape.CLOVER, CardNumber.THREE)
    );

    @DisplayName("플레이어가 21이 넘을때까지 카드를 뽑는다")
    @Test
    void test1() {

        //given
        FakeTrumpCardManager trumpCardManager = new FakeTrumpCardManager(cardDeck);
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"), playersBettingMoney,
                trumpCardManager);
        Player player = gameManager.findPlayerByUsername("레몬");

        //when
        while (!player.isImpossibleDraw()) {
            gameManager.drawMoreCard(player);
        }

        //then
        Assertions.assertThat(player.getCardDeck().calculateScore()).isGreaterThanOrEqualTo(21);
    }

    @DisplayName("플레이어는 dealer 혹은 딜러이름을 사용할 수 없다.")
    @Test
    void test2() {

        //given
        TrumpCardManager trumpCardManager = new TrumpCardManager();
        List<String> dealer1 = List.of("dealer");
        List<String> dealer2 = List.of("딜러");

        //when & then
        SoftAssertions.assertSoftly((softAssertions) -> {
            softAssertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> GameManager.initailizeGameManager(dealer1, playersBettingMoney, trumpCardManager))
                    .withMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
            softAssertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> GameManager.initailizeGameManager(dealer2, playersBettingMoney, trumpCardManager))
                    .withMessage("dealer 혹은 딜러는 이름으로 사용할 수 없습니다.");
        });
    }


    private static class FakeTrumpCardManager extends TrumpCardManager {

        private final Queue<TrumpCard> fakeTrumpCards;

        public FakeTrumpCardManager(List<TrumpCard> trumpCards) {
            this.fakeTrumpCards = new LinkedList<>(trumpCards);
        }

        @Override
        public TrumpCard drawCard() {
            return fakeTrumpCards.poll();
        }
    }
}
