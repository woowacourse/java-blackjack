package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.game.BettedMoney;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 이름과_베팅금액으로_참가자를_생성한다() {
        // Given
        PlayerName name = new PlayerName("프리");
        BettedMoney bettedMoney = new BettedMoney(30_000);

        // When & Then
        assertThatCode(() -> new Participant(name, bettedMoney))
                .doesNotThrowAnyException();
    }

    @Test
    void 참가자에게_카드를_한장_준다() {
        // Given
        PlayerName name = new PlayerName("프리");
        BettedMoney bettedMoney = new BettedMoney(30_000);
        Participant participant = new Participant(name, bettedMoney);

        // When & Then
        participant.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        assertThat(participant.getReceivedCards().size()).isEqualTo(1);
    }
}
