package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private Players players;

    @BeforeEach
    void init() {
        Player player1 = new Player(new Name("pobi"),
                new Cards(Arrays.asList(Card.valueOf(Denomination.JACK, Suit.CLOVER)
                        , Card.valueOf(Denomination.KING, Suit.HEART))));
        Player player2 = new Player(new Name("jason"),
                new Cards(Arrays.asList(Card.valueOf(Denomination.ACE, Suit.CLOVER)
                        , Card.valueOf(Denomination.JACK, Suit.HEART))));
        players = new Players(Arrays.asList(player1, player2));
    }

    @Test
    @DisplayName("현재 게임 플레이어를 반환한다.")
    void get_present_player() {
        String presentPlayerName = players.getPresentPlayer().getName();
        assertThat(presentPlayerName).isEqualTo("pobi");
    }

    @Test
    @DisplayName("현재 게임 플레이어가 턴을 종료했는지 확인한다.")
    void is_present_player_finished() {
        assertThat(players.isPresentPlayerFinished()).isFalse();
    }

    @Test
    @DisplayName("다음 게임 플레이어로 턴을 넘긴다")
    void pass_to_next_player() {
        players.passToNextPlayer();
        String presentPlayerName = players.getPresentPlayer().getName();
        assertThat(presentPlayerName).isEqualTo("jason");
    }

    @Test
    @DisplayName("현재 게임 플레이어의 상태를 stay로 만든다")
    void make_present_player_stay() {
        players.makePresentPlayerStay();
        assertThat(players.getPresentPlayer().isStay()).isTrue();
    }

    @Test
    @DisplayName("현재 게임 플레이어가 카드를 한장 뽑는다")
    void draw_card_present_player() {
        players.drawCardPresentPlayer(Card.valueOf(Denomination.JACK, Suit.HEART));
        List<Card> presentPlayerCards = players.getPresentPlayer().getCards().getValue();
        assertThat(presentPlayerCards).contains(Card.valueOf(Denomination.JACK, Suit.HEART));
    }

    @Test
    @DisplayName("모든 게임 플레이어가 턴을 종료했는지 확인한다.")
    void is_all_players_finished() {
        players.drawCardPresentPlayer(Card.valueOf(Denomination.JACK, Suit.HEART));

        assertThat(players.isAllFinished()).isTrue();
    }
}
