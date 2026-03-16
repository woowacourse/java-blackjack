package domain.member;

import domain.card.Card;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MembersTest {

    private final Money defaultMoney = new Money(10_000);

    @DisplayName("멤버 명단을 정상적으로 불러오는지 테스트")
    @Test
    void getAllPlayerName_MembersCreated_ContainsNames() {
        String player1 = "pobi";
        String player2 = "jason";
        Map<String, Money> playerBets = Map.of(player1, defaultMoney, player2, defaultMoney);

        Members members = new Members(playerBets);

        assertThat(members.getAllPlayerName()).contains(player1, player2);
    }

    @DisplayName("해당 멤버에게 카드를 주면 정상적으로 카드를 보유하고 있는지 테스트")
    @Test
    void provideCardToPlayer_CardGiven_MemberContainsCard() {
        String playerName = "pobi";
        Members members = new Members(Map.of(playerName, defaultMoney));
        Card card = Card.from("2", "하트");

        members.provideCardToPlayer(playerName, card);

        assertThat(members.findCardByName(playerName)).contains(card);
    }

    @DisplayName("모든 멤버의 수익 정산이 정상적으로 작동하는지 테스트")
    @Test
    void calculateFinalProfits_GameOver_ReturnsCorrectResults() {
        String playerName = "pobi";
        Members members = new Members(Map.of(playerName, new Money(10_000)));
        members.provideCardToPlayer(playerName, Card.from("Q", "하트"));
        members.provideCardToPlayer(playerName, Card.from("K", "하트"));
        members.changePlayerStateToStay(playerName);
        members.provideCardToDealer(Card.from("6", "하트"));
        members.provideCardToDealer(Card.from("4", "하트"));
        members.provideCardToDealer(Card.from("7", "하트"));
        members.changeDealerStateToStay();
        Map<String, Integer> results = members.calculateFinalProfits();

        assertThat(results.get(playerName)).isEqualTo(10_000);
        assertThat(results.get(members.getDealerName())).isEqualTo(-10_000);
    }

    @DisplayName("딜러와 플레이어가 모두 블랙잭이면 수익은 0원이다")
    @Test
    void calculateFinalProfits_BothBlackjack_ReturnsZero() {
        String playerName = "pobi";
        Members members = new Members(Map.of(playerName, new Money(10_000)));
        members.provideCardToPlayer(playerName, Card.from("A", "하트"));
        members.provideCardToPlayer(playerName, Card.from("K", "하트"));
        members.provideCardToDealer(Card.from("A", "스페이드"));
        members.provideCardToDealer(Card.from("Q", "스페이드"));

        Map<String, Integer> results = members.calculateFinalProfits();

        assertThat(results.get(playerName)).isEqualTo(0);
        assertThat(results.get(members.getDealerName())).isEqualTo(0);
    }

    @DisplayName("딜러가 버스트되면 Bust되지 않은 플레이어는 무조건 승리한다")
    @Test
    void calculateFinalProfits_DealerBust_PlayerWins() {
        String playerName = "pobi";
        Members members = new Members(Map.of(playerName, new Money(10_000)));
        members.provideCardToPlayer(playerName, Card.from("2", "하트"));
        members.provideCardToPlayer(playerName, Card.from("Q", "하트"));
        members.changePlayerStateToStay(playerName);
        members.provideCardToDealer(Card.from("10", "하트"));
        members.provideCardToDealer(Card.from("6", "하트"));
        members.provideCardToDealer(Card.from("7", "하트"));
        Map<String, Integer> results = members.calculateFinalProfits();

        assertThat(results.get(playerName)).isEqualTo(10_000);
        assertThat(results.get(members.getDealerName())).isEqualTo(-10_000);
    }
}
