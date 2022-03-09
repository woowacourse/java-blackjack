package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSymbol;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static final Card CLOVER2 = Card.of(CardRank.TWO, CardSymbol.CLOVER);
    private static final Card CLOVER4 = Card.of(CardRank.FOUR, CardSymbol.CLOVER);
    private static final Card CLOVER5 = Card.of(CardRank.FIVE, CardSymbol.CLOVER);
    private static final Card CLOVER6 = Card.of(CardRank.SIX, CardSymbol.CLOVER);
    private static final Card CLOVER10 = Card.of(CardRank.TEN, CardSymbol.CLOVER);
    private static final Card CLOVER_KING = Card.of(CardRank.KING, CardSymbol.CLOVER);

    private Player player;

    @BeforeEach
    void setUp() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);
        player = new Player("hudi", cardBundle);
    }

    @DisplayName("Player 인스턴스가 생성된다.")
    @Test
    void constructor() {
        assertThat(player).isNotNull();
    }

    @DisplayName("Card 를 전달받아 CardBundle 에 추가할 수 있다.")
    @Test
    void receiveCard() {
        player.receiveCard(CLOVER6);

        Set<Card> actual = player.getCardBundle().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("Score 가 21을 넘지 않으면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan21() {
        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21이면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOn21() {
        player.receiveCard(CLOVER2);
        player.receiveCard(CLOVER10);

        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21을 초과하면 false 를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan21() {
        player.receiveCard(CLOVER10);
        player.receiveCard(CLOVER_KING);

        boolean actual = player.canReceive();

        assertThat(actual).isFalse();
    }
}
