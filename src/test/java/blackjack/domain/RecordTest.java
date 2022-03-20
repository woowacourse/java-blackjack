package blackjack.domain;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.machine.Record;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class RecordTest {
    private Player player;
    private Dealer dealer;
    private Blackjack blackjack;

    @BeforeEach
    void setUp() {
        Map<String, Long> playersInfo = new LinkedHashMap<>();
        playersInfo.put("범블비", 1000L);

        Players players = new Players(playersInfo);
        dealer = new Dealer();
        blackjack = new Blackjack();
        player = players.getPlayers().get(0);

        //dealer: 12점, player: 12점
        player.addCard(Card.FIVE_CLOVER);
        player.addCard(Card.SEVEN_CLOVER);

        dealer.addCard(Card.FIVE_DIAMOND);
        dealer.addCard(Card.SEVEN_HEART);
    }

    @DisplayName("딜러만 버스트일 경우 전적 테스트")
    @Test
    void dealerBurst() {
        dealer.addCard(Card.J_HEART);

        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.VICTORY);
    }

    @DisplayName("플레이어만 버스트일 경우 전적 테스트")
    @Test
    void playerBurst() {
        player.addCard(Card.J_DIAMOND);

        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
    }

    @DisplayName("딜러, 플레이어 모두 버스트일 경우 전적 테스트")
    @Test
    void dealerPlayerBurst() {
        player.addCard(Card.J_CLOVER);
        dealer.addCard(Card.J_SPADE);

        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
    }

    @DisplayName("버스트가 아닌 경우 무승부 전적 테스트")
    @Test
    void ordinaryDrawRecord() {
        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DRAW);
    }

    @DisplayName("버스드가 아닌 경우 딜러 승리 전적 테스트")
    @Test
    void ordinaryDefeatTest() {
        player.addCard(Card.SEVEN_HEART);
        dealer.addCard(Card.NINE_HEART);

        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
    }

    @DisplayName("버스드가 아닌 경우 플레이어 승리 전적 테스트")
    @Test
    void ordinaryVictoryTest() {
        player.addCard(Card.NINE_HEART);
        dealer.addCard(Card.SEVEN_HEART);

        assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.VICTORY);
    }
}
