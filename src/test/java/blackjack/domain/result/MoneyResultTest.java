package blackjack.domain.result;

import static blackjack.domain.participants.HandTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

class MoneyResultTest {
    @DisplayName("decideWinner()이 참가자들의 Result를 올바르게 바꾸는지")
    @Test
    void judgeTest() {
        Dealer dealer = new Dealer();
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");

        CARDS_21_ACE_AS_ELEVEN.forEach(dealer::draw);
        CARDS_8.forEach(pobi::draw);
        CARDS_22_BUSTED.forEach(jason::draw);

        Participants participants = new Participants(dealer, pobi, jason);
        MoneyChanger moneyChanger = new MoneyChanger(participants);
        moneyChanger.receive(pobi, "1000");
        moneyChanger.receive(jason, "1500");
        MoneyResult result = new MoneyResult(participants, moneyChanger);
        String expectedToString = "딜러: 2500\npobi: -1000\njason: -1500";
        assertThat(result).hasToString(expectedToString);
    }
}
