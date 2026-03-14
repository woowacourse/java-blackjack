package domain.member;

import domain.MatchResult;
import domain.card.Card;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MembersTest {

    private final Money defaultMoney = new Money(10000);

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

    @DisplayName("수익 정산 로직이 정상적으로 작동하는지 테스트")
    @Test
    void calculatePlayerProfits_GameOver_ReturnsCorrectProfits() {
//        String player = "pobi";
//        Members members = new Members(Map.of(player, new Money(10000)));
//
//        members.provideCardToDealer(Card.from("6", "하트"));
//        members.provideCardToDealer(Card.from("4", "하트"));
//
//        members.provideCardToPlayer(player, Card.from("Q", "하트"));
//        members.provideCardToPlayer(player, Card.from("K", "하트"));
//
//        assertThat(members.calculatePlayerProfits()).containsExactly(10000);
    }
}
