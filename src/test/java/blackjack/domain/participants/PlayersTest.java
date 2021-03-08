package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.ResultType;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.dto.GameResult;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
    }

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        assertThatThrownBy(() -> Players.valueOf("a,b,a", deck))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        Dealer dealer = new Dealer(deck);
        Player pobi = new Player("pobi", deck);
        Player jason = new Player("jason", deck);
        Player root = new Player("root", deck);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        GameResult gameResult = players.match(dealer);

        Map<Player, ResultType> expected = new HashMap<>();
        expected.put(pobi, ResultType.WIN);
        expected.put(jason, ResultType.TIE);
        expected.put(root, ResultType.LOSE);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }

    @Test
    @DisplayName("모든 플레이어가 승부할 준비가 되었을 때 nextPlayerToPrepare 호출 시 예외처리")
    void nextPlayerToPrepare() {
        Dealer dealer = new Dealer(deck);
        Player pobi = new Player("pobi", deck);
        Player jason = new Player("jason", deck);
        Player root = new Player("root", deck);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        players.nextPlayerToPrepare();
        players.nextPlayerToPrepare();
        players.nextPlayerToPrepare();

        assertThatIllegalStateException().isThrownBy(() ->
            players.nextPlayerToPrepare())
            .withMessage("이미 모든 플레이어가 준비가 되었습니다.");
    }
}
