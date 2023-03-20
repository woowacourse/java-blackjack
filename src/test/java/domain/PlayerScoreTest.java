package domain;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.Cards;
import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.PlayerScore;
import domain.result.Profit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerScoreTest {

    @Test
    @DisplayName("플레이어 이름과 수익을 입력하면 playerScore 객체가 정상적으로 생성된다")
    void generatePlayerScore() {
        Name name = new Name("roy");
        Cards cards = new Cards(List.of(new Card(CardType.SPADE, CardValue.ACE), new Card(CardType.HEART, CardValue.KING)));
        Money money = new Money(1000);
        Player player = new Player(name, cards, money);
        Profit profit = Profit.winnerProfit(player);

        assertDoesNotThrow(() -> new PlayerScore(player.getName(), profit));
    }
}
