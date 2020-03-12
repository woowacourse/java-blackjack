package blackjack.domain;

import static blackjack.domain.participants.HandTest.*;
import static blackjack.domain.participants.Result.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;

public class RuleTest {

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
        Rule.judge(participants);

        assertThat(pobi.gameResult()).isEqualTo(LOSE.getValue());
        assertThat(jason.gameResult()).isEqualTo(LOSE.getValue());
        assertThat(dealer.countResult(WIN)).isEqualTo(2);
        assertThat(dealer.countResult(LOSE)).isEqualTo(0);
    }
}
