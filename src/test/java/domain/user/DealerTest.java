package domain.user;

import domain.CardNumber;
import domain.CardShape;
import domain.GameManager;
import domain.TrumpCard;
import domain.TrumpCardManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private final List<TrumpCard> cardDeck = List.of(
            new TrumpCard(CardShape.CLOVER, CardNumber.ACE),
            new TrumpCard(CardShape.CLOVER, CardNumber.TWO),
            new TrumpCard(CardShape.CLOVER, CardNumber.FOUR),
            new TrumpCard(CardShape.HEART, CardNumber.ACE),
            new TrumpCard(CardShape.HEART, CardNumber.TWO),
            new TrumpCard(CardShape.HEART, CardNumber.FOUR),
            new TrumpCard(CardShape.DIA, CardNumber.ACE),
            new TrumpCard(CardShape.DIA, CardNumber.TWO),
            new TrumpCard(CardShape.DIA, CardNumber.FOUR)
    );


    @DisplayName("딜러의 모든 카드를 연다")
    @Test
    void test() {

        //given
        FakeTrumpCardManager fakeTrumpCardManager = new FakeTrumpCardManager(cardDeck);
        Map<String, Betting> playerBetting = new HashMap<>();
        playerBetting.put("레몬", new Betting(11000));

        GameManager gameManager = GameManager.initailizeGameManager(playerBetting, new TrumpCardManager());
        Dealer dealer = gameManager.getDealer();

        //when
        for (int i = 0; i < 5; i++) {
            gameManager.drawMoreCard(dealer);
        }

        //then
        Assertions.assertThat(dealer.openAllCard()).hasSize(5);
    }

    @DisplayName("딜러가 16이 넘을때까지 카드를 뽑는다")
    @Test
    void test1() {

        //given
        FakeTrumpCardManager fakeTrumpCardManager = new FakeTrumpCardManager(cardDeck);
        Map<String, Betting> playerBetting = new HashMap<>();
        playerBetting.put("레몬", new Betting(11000));

        GameManager gameManager = GameManager.initailizeGameManager(playerBetting, fakeTrumpCardManager);
        Dealer dealer = gameManager.getDealer();

        //when
        while (!dealer.isImpossibleDraw()) {
            gameManager.drawMoreCard(dealer);
        }

        //then
        Assertions.assertThat(dealer.getCardDeck().calculateScore()).isGreaterThanOrEqualTo(16);
    }

    @DisplayName("호출한 값이 딜러이다.")
    @Test
    void test2() {

        //given
        Map<String, Betting> playerBetting = new HashMap<>();
        playerBetting.put("레몬", new Betting(11000));

        GameManager gameManager = GameManager.initailizeGameManager(playerBetting, new TrumpCardManager());

        //when
        Dealer dealer = gameManager.getDealer();

        //thn
        Assertions.assertThat(dealer.isDealer()).isEqualTo(true);
    }

    @DisplayName("딜러의 이름은 딜러이다")
    @Test
    void test3() {

        //given
        Map<String, Betting> playerBetting = new HashMap<>();
        playerBetting.put("레몬", new Betting(11000));

        GameManager gameManager = GameManager.initailizeGameManager(playerBetting, new TrumpCardManager());

        //when
        Dealer dealer = gameManager.getDealer();

        //thn
        Assertions.assertThat(dealer.getName()).isEqualTo("딜러");
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
