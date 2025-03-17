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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserTest {

    private final List<Long> playersBettingMoney = List.of(300000L);

    private final List<TrumpCard> cardDeck = List.of(
            new TrumpCard(CardShape.CLOVER, CardNumber.J),
            new TrumpCard(CardShape.CLOVER, CardNumber.SEVEN),
            new TrumpCard(CardShape.CLOVER, CardNumber.FOUR),
            new TrumpCard(CardShape.CLOVER, CardNumber.THREE)
    );

    private final List<TrumpCard> cardDeck1 = List.of(
            new TrumpCard(CardShape.CLOVER, CardNumber.TWO),
            new TrumpCard(CardShape.CLOVER, CardNumber.THREE),
            new TrumpCard(CardShape.CLOVER, CardNumber.FOUR),
            new TrumpCard(CardShape.CLOVER, CardNumber.FIVE),
            new TrumpCard(CardShape.CLOVER, CardNumber.SIX)
    );


    @Nested
    @DisplayName("카드 공개")
    public class OpenCard {


        @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
        @Test
        void test() {
            // given
            FakeTrumpCardManager trumpCardManager = new FakeTrumpCardManager(cardDeck);
            GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"), List.of(new Betting(10000)),
                    trumpCardManager);
            Player user = gameManager.findPlayerByUsername("레몬");

            gameManager.firstHandOutCard();
            gameManager.drawMoreCard(user);
            gameManager.drawMoreCard(user);
            gameManager.drawMoreCard(user);

            // when
            List<TrumpCard> trumpCards = user.openCard();
            // then
            Assertions.assertThat(trumpCards).hasSize(5);

        }

        @DisplayName("딜러는 자신이 가진 하나의 카드만 공개해야 한다")
        @Test
        void test2() {

            // given
            FakeTrumpCardManager trumpCardManager = new FakeTrumpCardManager(cardDeck);
            GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"), List.of(new Betting(10000)),
                    trumpCardManager);
            gameManager.firstHandOutCard();
            User dealer = gameManager.getDealer();

            // when
            List<TrumpCard> trumpCards = dealer.openCard();
            // then
            Assertions.assertThat(trumpCards).hasSize(1);
        }
    }

    @DisplayName("카드를 뽑았을 때 21을 넘기면 버스트된다")
    @Test
    void test3() {
        //given
        FakeTrumpCardManager trumpCardManager = new FakeTrumpCardManager(cardDeck);
        GameManager gameManager = GameManager.initailizeGameManager(List.of("레몬"), List.of(new Betting(10000)),
                trumpCardManager);
        Player player = gameManager.findPlayerByUsername("레몬");

        //when
        gameManager.drawMoreCard(player);
        gameManager.drawMoreCard(player);
        gameManager.drawMoreCard(player);
        gameManager.drawMoreCard(player);

        //then
        Assertions.assertThat(player.isBurst()).isTrue();
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
