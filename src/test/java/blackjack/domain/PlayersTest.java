package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    private List<Player> playerList;
    private Deck deck;

    @BeforeEach
    void setUp() {
        playerList = new ArrayList<>();
        playerList.add(Player.of(Name.of("handa"), BetAmount.of(1000)));
        playerList.add(Player.of(Name.of("dalsu"), BetAmount.of(1000)));

        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        deck = Deck.of(cards);
    }

    @Test
    void 플레이어_목록_생성시_null이면_예외_발생한다() {
        List<Player> nullPlayerList = null;

        assertThatThrownBy(() -> Players.of(nullPlayerList))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("players 은 null 이 올 수 없습니다.");
    }

    @Test
    void 게임에_참가한_플레이어의_명수를_반환한다() {
        Players players = Players.of(playerList);
        assertThat(players.count()).isEqualTo(2);
    }

    @Test
    void 게임이_시작되면_모든_플레이어가_카드를_받는다() {
        Players players = Players.of(playerList);
        players.receiveCards(deck);

        List<Player> result = players.getPlayers();
        assertThat(result.get(0).countCards()).isEqualTo(2);
        assertThat(result.get(1).countCards()).isEqualTo(2);
    }
}