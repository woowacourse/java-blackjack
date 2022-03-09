package blackjack.domain;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    private List<Gambler> gamblers;
    private Dealer dealer;

    @BeforeEach
    void setup() {
        gamblers = List.of(new Gambler("포비"), new Gambler("돌범"), new Gambler("리차드"));
        dealer = new Dealer("딜러");
    }

    @Test
    @DisplayName("딜러에게 겜블러들을 전달해서 승패를 반환받는다")
    void judgeByDealer() {
        // given
        BlackJackResult blackJackResult = BlackJackResult.of(dealer, gamblers);

        // when


        // then
    }
}
