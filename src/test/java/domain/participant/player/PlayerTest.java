package domain.participant.player;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.WinStatus;
import domain.vo.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private final Dealer dealer = new Dealer(new HandCards(List.of(new Card(TrumpSuit.CLUB, TrumpNumber.JACK), new Card(TrumpSuit.SPADE, TrumpNumber.TEN)))); // 20
    private Player player;
    private HandCards handCards;

    @BeforeEach
    void 플레이어_초기화() {
        handCards = new HandCards(List.of(new Card(TrumpSuit.HEART, TrumpNumber.JACK), new Card(TrumpSuit.HEART, TrumpNumber.SIX))); // 점수 16
        player = new Player(new Name("테스트"), handCards);
    }

    @Test
    void 플레이어_승리_테스트() {
        player.drawCard(new Card(TrumpSuit.SPADE, TrumpNumber.FIVE)); // 플레이어 점수 21
        player.calculateResult(dealer);
        assertThat(player.getWinStatus()).isEqualTo(WinStatus.WIN);
    }

    @Test
    void 플레이어_무승부_테스트() { // 플레이어 점수 20
        player.drawCard(new Card(TrumpSuit.SPADE, TrumpNumber.FOUR));
        player.calculateResult(dealer);
        assertThat(player.getWinStatus()).isEqualTo(WinStatus.DRAW);
    }

    @Test
    void 플레이어_패배_테스트() {
        player.drawCard(new Card(TrumpSuit.HEART, TrumpNumber.THREE)); // 19
        player.calculateResult(dealer);
        assertThat(player.getWinStatus()).isEqualTo(WinStatus.LOSS);
    }
}