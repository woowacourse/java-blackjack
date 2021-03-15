package blackjack.domain.gamer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hands;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players 객체 생성 성공")
    @Test
    void create() {
        CardManager cardManager = CardManager.create();
        assertThatCode(() ->
                new Players(
                        Arrays.asList(
                                new Player("joanne", 1000, cardManager.giveFirstHand()),
                                new Player("pk", 1000, cardManager.giveFirstHand())
                        )
                )
        ).doesNotThrowAnyException();
    }

    @DisplayName("블랙잭일 경우 상금의 1.5배 획득")
    @Test
    void calculateProfit() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.CLUB, Denomination.KING));
        cards.add(Card.of(Suit.CLUB, Denomination.ACE));
        Hands hands = new Hands(cards);
        Player player = new Player("pk", 1000, hands);
        assertThat(player.calculateProfit(ResultType.WIN)).isEqualTo(1500);
    }
}
