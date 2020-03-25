package blackjack.domain.result;

import static blackjack.domain.participants.HandTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.rule.MoneyRule;

public class BasicResultTest {

    @DisplayName("BasicResult의 judge() 메서드가 예상하는 결과값대로 나오는지 테스트")
    @Test
    void judgeTest() {

        Dealer dealer = new Dealer();
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        Player bingbong = new Player("bingbong");

        CARDS_8.forEach(dealer::draw);
        CARDS_21_ACE_AS_ONE.forEach(pobi::draw);
        CARDS_8.forEach(jason::draw);
        CARDS_22_BUSTED.forEach(bingbong::draw);

        List<Player> players = Arrays.asList(pobi, jason, bingbong);
        Participants participants = new Participants(dealer, players);

        BasicResult basicResult = new BasicResult();
        basicResult.judge(participants);

        Map<MoneyRule, Integer> dealerResult = basicResult.getDealerResult();
        Map<Participant, MoneyRule> playerResults = basicResult.getPlayerResults();

        assertThat(dealerResult.get(MoneyRule.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(MoneyRule.DRAW)).isEqualTo(1);
        assertThat(dealerResult.get(MoneyRule.LOSE)).isEqualTo(1);

        assertThat(playerResults.get(pobi)).isEqualTo(MoneyRule.WIN);
        assertThat(playerResults.get(jason)).isEqualTo(MoneyRule.DRAW);
        assertThat(playerResults.get(bingbong)).isEqualTo(MoneyRule.LOSE);
    }
}
