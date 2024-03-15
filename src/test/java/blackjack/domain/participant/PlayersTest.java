package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.game.Result;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어가 없으면 예외가 발생한다.")
    @Test
    void testCreatePlayersWithEmptyEntry() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자들 중 이름이 중복되는 경우는 예외가 발생한다.")
    @Test
    void testCreatePlayersWithDuplicateNames() {
        Player player1 = new Player(new Name("pobi"));
        Player player = new Player(new Name("pobi"));

        assertThatThrownBy(() -> new Players(List.of(player1, player)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("생성 검증을 모두 통과하면 생성에 성공한다.")
    @Test
    void testCreateWithValidPlayers() {
        Player player1 = new Player(new Name("pobi"));
        Player player = new Player(new Name("jason"));

        assertThatCode(() -> new Players(List.of(player1, player)))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 플레이어 이름 리스트로 플레이어들을 생성한다.")
    @Test
    void testCreateByNameList() {
        // given
        List<String> playerNames = List.of("pobi", "jason");

        // when
        Players players = Players.create(playerNames);

        // then
        assertThat(players.getPlayers()).containsExactly(
                new Player(new Name("pobi")),
                new Player(new Name("jason"))
        );
    }

    @DisplayName("모든 플레이어의 승패를 결정한다.")
    @Test
    void judge() {
        // given
        Hand dealerHand = new Hand(List.of(
                new Card(CardRank.THREE, CardSuit.DIAMOND),
                new Card(CardRank.NINE, CardSuit.CLUB),
                new Card(CardRank.EIGHT, CardSuit.DIAMOND)
        ));
        Dealer dealer = new Dealer(dealerHand);

        Hand pobiHand = new Hand(List.of(
                new Card(CardRank.TWO, CardSuit.HEART),
                new Card(CardRank.EIGHT, CardSuit.SPADE),
                new Card(CardRank.ACE, CardSuit.CLUB)
        ));
        Player pobi = new Player(new Name("pobi"), pobiHand);

        Hand jasonHand = new Hand(List.of(
                new Card(CardRank.SEVEN, CardSuit.CLUB),
                new Card(CardRank.KING, CardSuit.SPADE)
        ));
        Player jason = new Player(new Name("jason"), jasonHand);

        Players players = new Players(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.getResults()).containsExactlyEntriesOf(
                Map.of(
                        pobi, Result.WIN,
                        jason, Result.LOSE
                )
        );
    }
}
