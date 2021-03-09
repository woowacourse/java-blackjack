package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.CardDistributor;
import blackjack.domain.Response;
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

    private CardDistributor cardDistributor;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
        cardDistributor = new CardDistributor(deck);
    }

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
        Dealer dealer = new Dealer();
        Players players = Players.valueOf("pobi,jason,root");
        Participants participants = Participants.valueOf(dealer, players);
        cardDistributor.distributeStartingCardsTo(participants);

        GameResult gameResult = players.match(dealer);

        Map<Player, ResultType> expected = new HashMap<>();
        expected.put(new Player("pobi"), ResultType.WIN);
        expected.put(new Player("jason"), ResultType.TIE);
        expected.put(new Player("root"), ResultType.LOSE);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }

    @Test
    @DisplayName("모든 플레이어가 승부할 준비가 되었을 때 nextPlayerToPrepare 호출 시 예외처리")
    void nextPlayerToPrepare() {
        Players players = Players.valueOf("pobi,jason,root");
        for (int i = 0; i < 3; i++) {
            players.nextPlayerToPrepare().updateStatusByResponse(Response.NEGATIVE);
        }

        assertThatIllegalStateException().isThrownBy(players::nextPlayerToPrepare)
            .withMessage("이미 모든 플레이어가 준비가 되었습니다.");
    }
}
