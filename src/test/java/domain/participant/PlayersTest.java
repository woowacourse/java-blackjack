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
    List<Card> cards_21 = new ArrayList<>(Arrays.asList(card_A, card_Q));
    List<Card> cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    List<List<Card>> initCards = new ArrayList<>(Arrays.asList(cards_21, cards_BURST));
    List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"));
    Dealer dealer_17 = new Dealer(List.of(card_A, card_6));

    Players players;

    @BeforeEach
    void setUp() {
        players = new Players(names, initCards);
    }

    @Test
    @DisplayName("이름으로 플레이어 손패 반환")
    void showHandByName() {
        Name name = new Name("pobi");
        assertThat(players.showHandByName(name)).isEqualTo("pobi카드: A클로버, Q클로버");
    }

    @Test
    @DisplayName("모든 플레이어 손패 반환")
    void showHands() {
        assertThat(players.showHands()).isEqualTo(List.of("pobi카드: A클로버, Q클로버", "jason카드: K클로버, Q클로버, 2클로버"));
    }

    @Test
    @DisplayName("모든 플레이어 손패와 베스트 스코어 반환")
    void showHandsAndBestScores() {
        assertThat(players.showHandsAndBestScores()).isEqualTo(
                List.of("pobi카드: A클로버, Q클로버 - 결과 : 21", "jason카드: K클로버, Q클로버, 2클로버 - 결과 : 22"));
    }

    @Test
    @DisplayName("이름으로 플레이어 카드 추가")
    void addCardByName() {
        Name name = new Name("pobi");
        players.addCardByName(name, new Card(Rank.RANK_A, Suit.DIAMOND));
        assertThat(players.showHandByName(name)).isEqualTo("pobi카드: A클로버, Q클로버, A다이아몬드");
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
        assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("이름으로 플레이어가 블랙잭 인지 판별")
    void isBlackJackByName() {
        Name name = new Name("pobi");
        assertThat(players.isBlackJackByName(name)).isTrue();
    }

    @Test
    @DisplayName("플레이어들 중 블랙직이 존재하는지 판별")
    void iExistBlackJack() {
        assertThat(players.isExistBlackJack()).isTrue();
    }

    @Test
    @DisplayName("딜러를 포함한 참가자들 중 블랙잭이 있을 경우 결과 반환")
    void getResultAtBlackJack() {
        Map<Name, Versus> resultMap = players.getResultAtDealerBlackJack(dealer_17);
        assertThat(resultMap.get(new Name("pobi"))).isEqualTo(Versus.WIN);
        assertThat(resultMap.get(new Name("jason"))).isEqualTo(Versus.LOSE);
    }

    @Test
    @DisplayName("최종 게임 결과 반환")
    void getResultAtFinal() {
        Map<Name, Versus> resultMap = players.getResultAtDealerBlackJack(dealer_17);
        assertThat(resultMap.get(new Name("pobi"))).isEqualTo(Versus.WIN);
        assertThat(resultMap.get(new Name("jason"))).isEqualTo(Versus.LOSE);
    }
}
