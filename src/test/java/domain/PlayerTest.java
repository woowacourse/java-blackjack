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
import org.junit.jupiter.api.Test;

class PlayerTest {
    Player bustPlayer = Player.of(Name.from("pobi"), new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.J, CardShape.CLOVER),
                    Card.of(CardNumber.K, CardShape.HEART),
                    Card.of(CardNumber.Q, CardShape.DIAMOND)))),
            BettingMoney.of(1000));

    Player normalPlayer = Player.of(Name.from("jason"), new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.J, CardShape.CLOVER),
                    Card.of(CardNumber.Q, CardShape.DIAMOND)))),
            BettingMoney.of(2000));

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
