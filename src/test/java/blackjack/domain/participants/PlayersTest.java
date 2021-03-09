package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.CardDistributor;
import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Names;
import blackjack.dto.GameResult;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private CardDistributor cardDistributor;

    private Players newPlayers(String unparsedNames) {
        Names names = Names.valueOf(unparsedNames);
        return names.unwrap().stream()
            .map(name -> new Player(name, Betting.valueOf("1000")))
            .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

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
        assertThatThrownBy(() -> newPlayers("a,b,a"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        Dealer dealer = new Dealer();
        Players players = newPlayers("pobi,jason,root");
        Participants participants = Participants.valueOf(dealer, players);
        cardDistributor.distributeStartingCardsTo(participants);

        GameResult gameResult = players.match(dealer);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("pobi", 1000);
        expected.put("jason", 0);
        expected.put("root", -1000);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }

    @Test
    @DisplayName("모든 플레이어가 승부할 준비가 되었을 때 nextPlayerToPrepare 호출 시 예외처리")
    void nextPlayerToPrepare() {
        Players players = newPlayers("pobi,jason,root");
        for (int i = 0; i < 3; i++) {
            players.nextPlayerToPrepare().updateStatusByResponse(Response.NEGATIVE);
        }

        assertThatIllegalStateException().isThrownBy(players::nextPlayerToPrepare)
            .withMessage("이미 모든 플레이어가 준비가 되었습니다.");
    }
}
