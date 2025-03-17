package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    void 딜러와_플레이어를_입력받아_딜러의_승패를_반환한다() {
        // given
        Dealer dealer = Dealer.of(CardDeck.of());
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        Player player = Player.of("pobi1", Money.of(1000));
        player.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        player.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        // when
        BlackjackResult result = BlackjackResult.getPlayerResult(dealer, player);

        // then
        assertThat(result).isEqualTo(BlackjackResult.WIN);
    }
}
