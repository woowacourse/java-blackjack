package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    void 딜러와_플레이어를_입력받아_딜러의_승패를_반환한다() {
        // given
        Dealer dealer = Dealer.of();
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        dealer.receive(Card.of(TrumpNumber.SIX, TrumpShape.CLUB));
        Player player = Player.of("pobi1");
        player.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        player.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        // when
        BlackjackResult result = BlackjackResult.getPlayerResult(dealer, player);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }

    @Test
    void 상태를_입력받아_반대되는_상태를_반환한다() {
        // given
        BlackjackResult win = BlackjackResult.WIN;
        BlackjackResult draw = BlackjackResult.DRAW;
        BlackjackResult lose = BlackjackResult.LOSE;

        // when
        BlackjackResult winOpposite = BlackjackResult.getOpposite(win);
        BlackjackResult drawOpposite = BlackjackResult.getOpposite(draw);
        BlackjackResult loseOpposite = BlackjackResult.getOpposite(lose);

        // then
        Assertions.assertAll(
                () -> assertThat(winOpposite).isEqualTo(BlackjackResult.LOSE),
                () -> assertThat(drawOpposite).isEqualTo(BlackjackResult.DRAW),
                () -> assertThat(loseOpposite).isEqualTo(BlackjackResult.WIN)
        );
    }
}
