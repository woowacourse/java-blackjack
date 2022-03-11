package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

class MatchRecordTest {

    @Test
    @DisplayName("플레이어와 딜러의 승패를 판단한다.")
    public void testMatchWithPlayerAndDealer() {
        // given
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Player player = new Player("player");
        
        player.drawCard(deck);
        
        // when
        MatchRecord record = MatchRecord.findMatchRecord(player, dealer);
        
        // then
        Assertions.assertThat(record).isEqualTo(MatchRecord.WIN);
    }

    @Test
    @DisplayName("승패를 뒤집을 수 있다.")
    public void testReverseRecord() {
        // given
        MatchRecord record = MatchRecord.WIN;
        // when
        MatchRecord reversed = record.reverseRecord();
        // then
        Assertions.assertThat(reversed).isEqualTo(MatchRecord.LOSS);
    }

    @Test
    @DisplayName("무승부는 뒤집어도 무승부다")
    public void testReverseRecordWithTie() {
        // given
        MatchRecord record = MatchRecord.TIE;
        // when
        MatchRecord reversed = record.reverseRecord();
        // then
        Assertions.assertThat(reversed).isEqualTo(MatchRecord.TIE);
    }
}