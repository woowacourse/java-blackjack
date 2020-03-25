package blackjack.domain.result;

import static blackjack.domain.participants.HandTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class MoneyResultTest {

    @DisplayName("MoneyResult에 있는 judge()를 수행하고 결과가 제대로 나오는지 테스트")
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

        MoneyResult moneyResult = new MoneyResult();

        moneyResult.initPlayerMoney(pobi, "4000"); // 승
        moneyResult.initPlayerMoney(jason, "5000"); // 무
        moneyResult.initPlayerMoney(bingbong, "6000"); //

        List<Player> players = Arrays.asList(pobi, jason, bingbong);
        Participants participants = new Participants(dealer, players);

        moneyResult.judge(participants);

        Money dealerResult = moneyResult.getDealerMoney();
        Map<Participant, Money> playerResults = moneyResult.getPlayersMoney();

        assertThat(dealerResult.getAmount()).isEqualTo(2000L);

        assertThat(playerResults.get(pobi).getAmount()).isEqualTo(4000L);
        assertThat(playerResults.get(jason).getAmount()).isEqualTo(0L);
        assertThat(playerResults.get(bingbong).getAmount()).isEqualTo(-6000L);

    }
}
