package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        assertThatThrownBy(() -> Players.valueOf("a,b,a"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        Player root = new Player("root");
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));

        dealer.drawCard(deck);
        dealer.drawCard(deck);

        pobi.drawCard(deck);
        pobi.drawCard(deck);

        jason.drawCard(deck);
        jason.drawCard(deck);

        root.drawCard(deck);
        root.drawCard(deck);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        GameResult gameResult = players.match(dealer);

        Map<Player, ResultType> expected = new HashMap<>();
        expected.put(pobi, ResultType.WIN);
        expected.put(jason, ResultType.TIE);
        expected.put(root, ResultType.LOSE);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }
}
