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

class BlackjackRuleTest {

    @Test
    void 딜러와_플레이어를_입력받아_딜러의_승패를_반환한다() {
        // given
        List<Card> cards = List.of(
                Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                Card.of(TrumpNumber.SIX, TrumpShape.CLUB)
        );
        CardDeck cardDeck = CardDeck.of(cards);
        Dealer dealer = Dealer.of();
        dealer.receive(cardDeck.popCard());
        dealer.receive(cardDeck.popCard());
        Player player = Player.of("pobi1");
        player.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        player.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        // when
        GameResult result = BlackjackRule.getPlayerResult(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }
}
