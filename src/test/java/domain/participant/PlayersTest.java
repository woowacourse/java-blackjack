package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.Results;
import domain.result.WinOrLose;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    Card card_A = Card.getCard(Rank.RANK_A, Suit.CLOVER);
    Card card_2 = Card.getCard(Rank.RANK_2, Suit.CLOVER);
    Card card_Q = Card.getCard(Rank.RANK_Q, Suit.CLOVER);
    Card card_K = Card.getCard(Rank.RANK_K, Suit.CLOVER);
    Card card_6 = Card.getCard(Rank.RANK_6, Suit.CLOVER);
    List<Card> cards_BLACKJACK = new ArrayList<>(Arrays.asList(card_A, card_Q));
    List<Card> cards_BUST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    Dealer dealer_17 = new Dealer(List.of(card_A, card_6));

    Player player_BLACKJACK;
    Player player_BUST;
    Players players;

    @BeforeEach
    void setUp() {
        player_BLACKJACK = new Player(new Name("player_BLACKJACK"), cards_BLACKJACK);
        player_BUST = new Player(new Name("player_BUST"), cards_BUST);
        players = new Players(List.of(player_BLACKJACK, player_BUST));
    }

    @Test
    @DisplayName("모든 플레이어가 Bust 인지 판별")
    void isBustAllBust() {
        assertThat(players.isNotAllBust()).isTrue();
    }

    @Test
    @DisplayName("최종 게임 결과 반환")
    void getResultAtFinal() {
        Results resultMap = Results.generateResults(dealer_17, players);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BLACKJACK"))).isEqualTo(WinOrLose.WIN);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BUST"))).isEqualTo(WinOrLose.LOSE);
    }
}
