package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.player.Gambler;
import domain.player.attribute.Name;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("이름 별로 베팅 금액 정상적으로 입력받는지 테스트")
    void 이름_리스트를_전달하면_각_이름에_대한_베팅_정보_맵을_생성한다() {
        // given
        List<String> nameValues = List.of("pobi", "woni");
        Map<Name, BettingAmount> gamblerBettingAmountInfo = new HashMap<Name, BettingAmount>();
        gamblerBettingAmountInfo.put(new Name("pobi"), new BettingAmount(10000));
        gamblerBettingAmountInfo.put(new Name("woni"), new BettingAmount(20000));

        Betting betting = new Betting(nameValues);

        betting.betBettingAmount(new Name("pobi"), new BettingAmount(10000));
        betting.betBettingAmount(new Name("woni"), new BettingAmount(20000));
        // then
        Assertions.assertAll(
                () -> Assertions.assertThat(betting.getBettingAmountByName(new Name("pobi")))
                        .isEqualTo(new BettingAmount(10000)),
                () -> Assertions.assertThat(betting.getBettingAmountByName(new Name("woni")))
                        .isEqualTo(new BettingAmount(20000))
        );
    }

}