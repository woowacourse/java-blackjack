//package domain.game;
//
//import static util.Constants.DEALER_NAME;
//
//import domain.card.Card;
//import domain.player.Dealer;
//import java.util.HashMap;
//import java.util.Map;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class GamblersGameResultTest {
//
//    @Test
//    @DisplayName("승패 결과 저장 검증")
//    void 승패_결과_저장_검증() {
//        // given
//        Map<String, Integer> gamblers = new HashMap<>();
//        gamblers.put("pobi",10);
//        gamblers.put("coco", 21);
//        gamblers.put("kaiya", 20);
//        Dealer dealer = new Dealer(DEALER_NAME);
//        dealer.addCard(new Card("K", "다이아몬드"));
//
//        // when
//        GamblersGameResult gameResult = new GamblersGameResult(dealer, gamblers);
//
//        // then
//        Assertions.assertThat(gameResult.getMatchResult("pobi")).isEqualTo(GameResult.LOSE);
//        Assertions.assertThat(gameResult.getMatchResult("coco")).isEqualTo(GameResult.WIN);
//        Assertions.assertThat(gameResult.getMatchResult("kaiya")).isEqualTo(GameResult.DRAW);
//    }
//}
