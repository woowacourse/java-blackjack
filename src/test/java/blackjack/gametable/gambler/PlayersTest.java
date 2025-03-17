package blackjack.gametable.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어들_일급_컬렉션을_생성한다() {
        // given
        PlayerName playerName1 = new PlayerName("John");
        PlayerName playerName2 = new PlayerName("Jeff");
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);

        // when
        Players players = new Players(List.of(player1, player2));

        // then
        assertThat(players.getPlayers()).hasSize(2);
    }

    @Test
    void 플레이어들_이름을_반환한다() {
        // given
        PlayerName playerName1 = new PlayerName("John");
        PlayerName playerName2 = new PlayerName("Jeff");
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        Players players = new Players(List.of(player1, player2));

        // when
        List<String> playerNames = players.getPlayerNames();

        // then
        assertThat(playerNames).hasSize(2);
        assertThat(playerNames.getFirst()).isEqualTo("John");
        assertThat(playerNames.getLast()).isEqualTo("Jeff");
    }

    @Test
    void 플레이어_이름으로_해당_플레이어를_반환한다() {
        // given
        PlayerName playerName1 = new PlayerName("John");
        PlayerName playerName2 = new PlayerName("Jeff");
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        Players players = new Players(List.of(player1, player2));

        // when
        Player player = players.findPlayer("Jeff");

        // then
        assertThat(player.getPlayerName()).isEqualTo("Jeff");
    }

    @Test
    void 플레이어들에게_초기_카드를_배부한다() {
        // given
        PlayerName playerName1 = new PlayerName("John");
        PlayerName playerName2 = new PlayerName("Jeff");
        Player player1 = new Player(playerName1);
        Player player2 = new Player(playerName2);
        Players players = new Players(List.of(player1, player2));

        Cards cards1 = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        Cards cards2 = createCards(
                createCard(TrumpRank.NINE, TrumpSuit.CLOVER),
                createCard(TrumpRank.TEN, TrumpSuit.HEART)
        );

        // when
        players.initializeHands(List.of(cards1, cards2));

        // then
        assertThat(player1.openInitialCards()).hasSize(2);
        assertThat(player2.openInitialCards()).hasSize(2);
        assertThat(player2.openInitialCards().getFirst().getRank()).isEqualTo(TrumpRank.NINE);
        assertThat(player2.openInitialCards().getFirst().getSuit()).isEqualTo(TrumpSuit.CLOVER);
    }

    private Cards createCards(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
    }

    private Card createCard(TrumpRank rank, TrumpSuit suit) {
        return new Card(rank, suit);
    }

}
