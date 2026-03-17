package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.hand.Hand;
import domain.participant.BettingMoney;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.PlayerInfo;
import org.junit.jupiter.api.Test;

class PlayerTest {
    Player bustPlayer = Player.of(new PlayerInfo(Name.from("pobi"), BettingMoney.of(1000)),
            new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.JACK, CardShape.CLOVER),
                    Card.of(CardNumber.KING, CardShape.HEART),
                    Card.of(CardNumber.QUEEN, CardShape.DIAMOND)))));

    Player normalPlayer = Player.of(new PlayerInfo(Name.from("jason"), BettingMoney.of(2000)),
            new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.JACK, CardShape.CLOVER),
                    Card.of(CardNumber.QUEEN, CardShape.DIAMOND)))));

    @Test
    void 플레이어_카드_추가_확인() {
        bustPlayer.addHandCard(Card.of(CardNumber.FIVE, CardShape.DIAMOND));
        normalPlayer.addHandCard(Card.of(CardNumber.FOUR, CardShape.DIAMOND));
        assertThat(bustPlayer.getHandCards().size()).isEqualTo(4);
        assertThat(normalPlayer.getHandCards().size()).isEqualTo(3);
    }

    @Test
    void 버스트_여부_확인() {
        assertThat(bustPlayer.isBust()).isTrue();
        assertThat(normalPlayer.isBust()).isFalse();
    }
}
