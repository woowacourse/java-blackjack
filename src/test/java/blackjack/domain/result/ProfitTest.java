//package blackjack.domain.result;
//
//import blackjack.domain.playing.user.User;
//import blackjack.domain.result.user.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ProfitTest {
//
//    @ParameterizedTest
//    @MethodSource("createUsersAndProfit")
////    void getProfitRate(User player, User dealer, Profit profit) {
////        assertThat(Profit.getProfitRate(player, dealer).getProfit()).isEqualTo(profit);
////    }
////
////    private static Stream<Arguments> createUsersAndProfit() {
////        return Stream.getProfitRate(
////                Arguments.getProfitRate(new BlackjackPlayer(), new ScoreTwentyDealer(), 1.5),
////                Arguments.getProfitRate(new BlackjackPlayer(), new BlackjackDealer(), 0.0),
////                Arguments.getProfitRate(new BustPlayer(), new ScoreTwentyDealer(), -1.0),
////                Arguments.getProfitRate(new BustPlayer(), new BustDealer(), -1.0),
////                Arguments.getProfitRate(new ScoreNineteenPlayer(), new ScoreEighteenDealer(), 1.0),
////                Arguments.getProfitRate(new ScoreNineteenPlayer(), new ScoreNineteenDealer(), 0.0),
////                Arguments.getProfitRate(new ScoreNineteenPlayer(), new ScoreTwentyDealer(), -1.0)
////        );
////    }
//
//    @Test
//    void sum() {
//    }
//
//    @Test
//    void opposite() {
//    }
//}