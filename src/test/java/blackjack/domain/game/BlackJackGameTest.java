package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGameTest {
    private BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
        blackJackGame = new BlackJackGame(names);
    }

    @Test
    @DisplayName("게임 초기화 시 각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        boolean match = blackJackGame.getPlayers()
                .getPlayers()
                .stream()
                .mapToInt(player -> player.getCards().getValue().size())
                .anyMatch(cardSize -> cardSize != 2);
        assertThat(match).isFalse();
    }

    @Test
    @DisplayName("모든 플레이어가 턴을 종료했는지 확인한다.")
    void is_all_players_finished() {
        for (Player player : blackJackGame.getPlayers().getPlayers()) {
            player.drawCard(Card.valueOf(Denomination.JACK, Suit.CLOVER)); //초기 2장 + 20 이므로 무조건 21이 넘는다.
            player.drawCard(Card.valueOf(Denomination.JACK, Suit.CLOVER));
        }

        assertThat(blackJackGame.isAllPlayersFinished()).isTrue();
    }

    @Test
    @DisplayName("현재 플레이어가 턴을 종료했는지 확인한다.")
    void is_present_player_finished() {
        Player presentPlayer = blackJackGame.getPresentPlayer();
        presentPlayer.drawCard(Card.valueOf(Denomination.JACK, Suit.CLOVER));
        presentPlayer.drawCard(Card.valueOf(Denomination.JACK, Suit.CLOVER));

        assertThat(blackJackGame.isPresentPlayerFinished()).isTrue();
    }

    @Test
    @DisplayName("다음 플레이어로 넘어가는지 확인한다.")
    void pass_to_next_player() {
        blackJackGame.passToNextPlayer();
        Player nextPlayer = blackJackGame.getPresentPlayer();

        assertThat(nextPlayer.getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("현재 플레이어가 카드를 더 받는걸 원한다면 카드를 한장 더 뽑는다.")
    void draw_present_player_success() {
        blackJackGame.drawPresentPlayer(true);
        Player presentPlayer = blackJackGame.getPresentPlayer();

        int cardSize = presentPlayer.getCards()
                .getValue()
                .size();

        assertThat(cardSize).isEqualTo(3);
    }

    @Test
    @DisplayName("현재 플레이어가 카드를 더 받는걸 원하지 않으면 플레이어 상태가 stay로 변한.")
    void draw_present_player_fail() {
        blackJackGame.drawPresentPlayer(false);
        Player presentPlayer = blackJackGame.getPresentPlayer();

        assertThat(presentPlayer.isStay()).isEqualTo(true);
    }

    @Test
    @DisplayName("현재 게임중인 플레이어를 반환한다.")
    void get_present_player() {
        Player presentPlayer = blackJackGame.getPresentPlayer();

        assertThat(presentPlayer.getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("딜러가 턴을 종료했는지 확인한다.")
    void is_dealer_finished() {
        Dealer dealer = blackJackGame.getDealer();
        dealer.drawCard(Card.valueOf(Denomination.EIGHT, Suit.CLOVER)); //초기 2장 + 14 이므로 무조건 16이 넘는다.
        dealer.drawCard(Card.valueOf(Denomination.SEVEN, Suit.CLOVER));

        assertThat(blackJackGame.isDealerFinished()).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 뽑는다.")
    void draw_dealer() {
        blackJackGame.drawDealer();
        Dealer dealer = blackJackGame.getDealer();

        int cardSize = dealer.getCards()
                .getValue()
                .size();

        assertThat(cardSize).isEqualTo(3);
    }
}
