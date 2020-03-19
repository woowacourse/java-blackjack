package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Type;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static blackjack.domain.participant.Dealer.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("name 속성의 값 \"딜러\"로 초기화 확인")
    @Test
    void test1() {
        Dealer dealer = new Dealer();

        String dealerName = dealer.name();

        assertThat(dealerName).isEqualTo(DEALER_NAME);
    }

    @DisplayName("추가로 카드를 받을 수 있는지 여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"SIX, true", "SEVEN, false"})
    void test2(Type type, boolean expectedPossibility) {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.TEN, Figure.CLOVER));
        dealer.addCard(Card.of(type, Figure.HEART));

        boolean actualPossibility = dealer.canGetMoreCard();

        assertThat(actualPossibility).isEqualTo(expectedPossibility);
    }

    @DisplayName("첫 번째 카드를 보여주는지 확인")
    @Test
    void test3() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.FIVE, Figure.CLOVER));
        dealer.addCard(Card.of(Type.EIGHT, Figure.HEART));

        List<String> firstCardInfo = dealer.showFirstCard();
        int cardInfoSize = firstCardInfo.size();

        assertThat(cardInfoSize).isEqualTo(1);
        assertThat(firstCardInfo).contains("5클로버");
    }

    @DisplayName("DealerResult 생성 확인")
    @Test
    void test4() {
        Dealer dealer = new Dealer();
        List<PlayerResult> playerResults = Arrays.asList(new PlayerResult(new Player(new Name("타미")), ResultType.WIN),
                new PlayerResult(new Player(new Name("포비")), ResultType.DRAW),
                new PlayerResult(new Player(new Name("쪼밀리")), ResultType.LOSE));

        HashMap<ResultType, Integer> dealerResult = new HashMap<>();
        dealerResult.put(ResultType.LOSE, 1);
        dealerResult.put(ResultType.DRAW, 1);
        dealerResult.put(ResultType.WIN, 1);

        DealerResult expectedResult = new DealerResult(new Name(DEALER_NAME), dealerResult);

        DealerResult actualResult = dealer.createDealerResult(playerResults);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
