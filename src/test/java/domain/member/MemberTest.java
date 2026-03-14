package domain.member;

import domain.MatchResult;
import domain.card.Card;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MemberTest {

//    @DisplayName("딜러와 플레이어의 승패를 가르고, 결과를 출력하는 테스트")
//    @ParameterizedTest
//    @CsvSource({
//            "5:4, 3:2, WIN",
//            "5:4, 6:3, DRAW",
//            "3:2, 5:4, LOSE",
//            "10:9:3, 8:7:6:5, WIN"
//    })
//    void winnerTest_CompareDealerAndPlayerScore_returnMatchResult(String dealerCardValue, String playerCardValue,
//                                                                  MatchResult expected) {
//        Member dealer = new Dealer();
//        Member player = new Player("브리");
//
//        Arrays.stream(dealerCardValue.split(":"))
//                .forEach(number -> dealer.receiveCard(new Card(number, "하트")));
//        Arrays.stream(playerCardValue.split(":"))
//                .forEach(number -> player.receiveCard(new Card(number, "하트")));
//
//        Assertions.assertEquals(expected, dealer.compareScoreWith(player));
//    }
}
