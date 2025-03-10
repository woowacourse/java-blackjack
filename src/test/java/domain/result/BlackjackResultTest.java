package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    void 딜러와_플레이어를_입력받아_딜러의_승패를_반환한다() {
        // given
        List<Card> cards = List.of(
                Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                Card.of(TrumpNumber.SIX, TrumpShape.CLUB)
        );
        Dealer dealer = Dealer.of(CardDeck.of(cards));
        dealer.receive(dealer.drawCard());
        dealer.receive(dealer.drawCard());
        Player player = Player.of("pobi1");
        player.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        player.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        // when
        BlackjackResult result = BlackjackResult.getPlayerResult(dealer, player);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }
}
