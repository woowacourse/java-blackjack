package domain.game;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersGameResultTest {

    @Test
    @DisplayName("승패 결과 저장 검증")
    void 승패_결과_저장_검증() {
        // given
        Gambler gambler1 = new Gambler(new Name("pobi"), new Hand(), new Money("10000"));
        gambler1.addCard(new Card("5", "하트"));

        Gambler gambler2 = new Gambler(new Name("coco"), new Hand(), new Money("10000"));
        gambler2.addCard(new Card("J", "하트"));

        Gambler gambler3 = new Gambler(new Name("kaiya"), new Hand(), new Money("10000"));
        gambler3.addCard(new Card("8", "하트"));

        Gamblers gamblers = new Gamblers(List.of(gambler1, gambler2, gambler3));

        Dealer dealer = new Dealer(new Name("딜러"), new Hand());
        dealer.addCard(new Card("8", "하트"));

        // when
        GamblersGameResult gameResult = new GamblersGameResult(dealer, gamblers);

        // then
        Assertions.assertThat(gameResult.getMatchResult("pobi")).isEqualTo(-10000);
        Assertions.assertThat(gameResult.getMatchResult("coco")).isEqualTo(10000);
        Assertions.assertThat(gameResult.getMatchResult("kaiya")).isEqualTo(0);
    }
}