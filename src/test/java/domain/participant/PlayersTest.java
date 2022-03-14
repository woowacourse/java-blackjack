package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.Versus;

public class PlayersTest {

    Card card_A = new Card(Rank.RANK_A, Suit.CLOVER);
    Card card_2 = new Card(Rank.RANK_2, Suit.CLOVER);
    Card card_Q = new Card(Rank.RANK_Q, Suit.CLOVER);
    Card card_K = new Card(Rank.RANK_K, Suit.CLOVER);
    Card card_6 = new Card(Rank.RANK_6, Suit.CLOVER);
    List<Card> cards_BLACKJACK = new ArrayList<>(Arrays.asList(card_A, card_Q));
    List<Card> cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    List<List<Card>> initCards = new ArrayList<>(Arrays.asList(cards_BLACKJACK, cards_BURST));
    List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
    Dealer dealer_17 = new Dealer(List.of(card_A, card_6));
    Dealer dealer_BLACKJACK = new Dealer(List.of(card_A, card_Q));

    Players players;

    @BeforeEach
    void setUp() {
        players = new Players(names, initCards);
    }

    @Test
    @DisplayName("이름으로 플레이어 손패 반환")
    void showHandByName() {
        Name name = new Name("pobi");
        assertThat(players.showHandByName(name)).isEqualTo("A클로버, Q클로버");
    }

    @Test
    @DisplayName("이름으로 플레이어 카드 추가")
    void addCardByName() {
        Name name = new Name("pobi");
        players.addCardByName(name, new Card(Rank.RANK_A, Suit.DIAMOND));
        assertThat(players.showHandByName(name)).isEqualTo("A클로버, Q클로버, A다이아몬드");
    }

    @Test
    @DisplayName("이름으로 플레이어가 Bust 인지 판별")
    void isBustByName() {
        Name name = new Name("jason");
        assertThat(players.isBustByName(name)).isTrue();
    }

    @Test
    @DisplayName("모든 플레이어가 Bust 인지 판별")
    void isBustAllBust() {
        assertThat(players.isNotAllBust()).isTrue();
    }

    @Test
    @DisplayName("이름으로 플레이어가 블랙잭 인지 판별")
    void isBlackJackByName() {
        Name name = new Name("pobi");
        assertThat(players.isMaxScoreByName(name)).isTrue();
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 결과 반환")
    void getResultAtDealerBlackJack() {
        Map<Name, Versus> resultMap = players.getResultAtDealerBlackJack(dealer_BLACKJACK);
        assertThat(resultMap.get(new Name("pobi"))).isEqualTo(Versus.DRAW);
        assertThat(resultMap.get(new Name("jason"))).isEqualTo(Versus.LOSE);
    }

    @Test
    @DisplayName("최종 게임 결과 반환")
    void getResultAtFinal() {
        Map<Name, Versus> resultMap = players.getResultAtFinal(dealer_17);
        assertThat(resultMap.get(new Name("pobi"))).isEqualTo(Versus.WIN);
        assertThat(resultMap.get(new Name("jason"))).isEqualTo(Versus.LOSE);
    }
}
