package domain.member;

import domain.MatchResult;
import domain.card.Card;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MembersTest {

    @DisplayName("멤버 명단을 정상적으로 불러오는지 테스트")
    @Test
    void getAllMemberNameTest_memberSuccessfullyCreated_containPlayerNames() {
        String player1 = "pobi";
        String player2 = "jason";

        Members members = new Members(List.of(player1, player2));

        Assertions.assertThat(members.getAllPlayerName()).contains(player1, player2);
    }

    @DisplayName("해당 멤버에게 카드를 주면 정상적으로 카드를 보유하고 있는지 테스트")
    @Test
    void provideCardToMember_cardGivenToMember_containGivenCard() {
        String playerName = "pobi";
        Members members = new Members(List.of(playerName));
        Card card = new Card("2", "하트");

        members.provideCardToPlayer(playerName, card);

        Assertions.assertThat(members.findCardByName(playerName)).contains(card);
    }

    @DisplayName("딜러의 게임 결과를 정상적으로 결정하는지 테스트")
    @Test
    void determineDealerGameResult_compareWithPlayer_returnMatchResult() {
        String player = "pobi";
        Members members = new Members(List.of(player));
        List<Card> dealerCards = List.of(new Card("6", "하트"), new Card("4", "하트"));
        List<Card> playerCards = List.of(new Card("2", "하트"), new Card("3", "하트"));
        MatchResult expected = MatchResult.WIN;

        dealerCards.forEach(members::provideCardToDealer);
        playerCards.forEach(card -> members.provideCardToPlayer(player, card));

        Assertions.assertThat(members.determineDealerGameResult()).containsExactly(expected);
    }

    @DisplayName("플레이어의 게임 결과를 정상적으로 결정하는지 테스트")
    @Test
    void determinePlayerGameResult_compareWithDealer_returnMatchResult() {
        String player = "pobi";
        Members members = new Members(List.of(player));
        List<Card> dealerCards = List.of(new Card("6", "하트"), new Card("4", "하트"));
        List<Card> playerCards = List.of(new Card("2", "하트"), new Card("3", "하트"));
        MatchResult expected = MatchResult.LOSE;

        dealerCards.forEach(members::provideCardToDealer);
        playerCards.forEach(card -> members.provideCardToPlayer(player, card));

        Assertions.assertThat(members.determinePlayerGameResult(player)).isEqualTo(expected);
    }
}
