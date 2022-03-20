package blackjack.domain.participant;

import blackjack.domain.card.*;
import blackjack.domain.gameresult.ProfitResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자의 이름으로 참가자를 찾는다.")
    public void findParticipantsByNameTest() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"aaa", "bbb", "ccc"});

        assertDoesNotThrow(() -> {
            participants.getUserByName("aaa");
        });
    }

    @Test
    @DisplayName("참가자의 이름으로 참가자를 찾지 못하면 예외를 발생시킨다.")
    public void findParticipantsByNameNotIncludeTest() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"aaa", "bbb", "ccc"});

        assertThatThrownBy(() -> {
            participants.getUserByName("zzz");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("딜러가 존재하지 않는 경우 딜러를 찾으려고 하면 예외를 발생시킨다.")
    public void findDealerNotInParticipantsTest() {
        Participants participants = new Participants();

        assertThatThrownBy(() -> participants.getDealer()).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("유저가 패배한 경우 Result로 패배를 반환한다.")
    public void checkUserLoseCase() {
        Participants participants = new Participants();
        participants.addDealer();
        participants.distributeCard(new Deck(new CardGenerator()));
        participants.addUsers(new String[]{"박창갑", "김환룡"});
        participants.getUserResults();
        assertThat(participants.getUserResults().get(0).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("유저가 승리한 경우 Result로 승리를 반환한다.")
    public void checkUserWinCase() {
        Participants participants = new Participants();
        participants.addUsers(new String[]{"박창갑", "김환룡"});
        participants.distributeCard(new Deck(new CardGenerator()));
        participants.addDealer();
        participants.getUserResults();
        assertThat(participants.getUserResults().get(0).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("참가자들 중에서 딜러가 있는 경우 해당 딜러를 찾는다.")
    public void findDealerInParticipantsTest() {
        Participants participants = new Participants();
        participants.addDealer();
        assertThat(participants.getDealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("카드를 분배받는 참가자들은 카드의 총합이 높아진다.")
    public void distributeCardTest() {
        Participants participants = new Participants();
        participants.addDealer();
        int beforeDistributeCardSum = participants.getDistributeResult().get(0).getCardSum();
        participants.distributeCard(new Deck(new CardGenerator()));
        int afterDistributeCardSum = participants.getDistributeResult().get(0).getCardSum();

        assertThat(beforeDistributeCardSum < afterDistributeCardSum).isTrue();
    }

    @Test
    @DisplayName("딜러가 아닌 유저의 정보만을 가져올 수 있다.")
    public void getUsersTest() {
        Participants participants = new Participants();
        participants.addDealer();
        participants.addUsers(new String[]{"pokemon", "yugioh"});

        assertThat(participants.getUsers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("2승 0무 0패의 상황에서 최종 딜러의 수익을 계산한다.")
    public void calculateProfitResultDealerWinTest() {
        Participants participants = makeParticipants();

        Participant kim = participants.getUserByName("kim");
        kim.receiveCard(Card.of(CardNumber.TEN, CardType.CLOVER));

        Participant park = participants.getUserByName("park");
        park.receiveCard(Card.of(CardNumber.FIVE, CardType.SPADE));

        Dealer dealer = participants.getDealer();
        dealer.receiveCard(Card.of(CardNumber.ACE, CardType.CLOVER));

        ProfitResult dealerProfitResult = participants.calculateProfitResult().get(0);
        assertThat(dealerProfitResult.getProfit()).isEqualTo(20000);
    }

    @Test
    @DisplayName("0승 0무 2패의 상황에서 최종 딜러의 수익을 계산한다.")
    public void calculateProfitResultDealerLoseTest() {
        Participants participants = makeParticipants();

        Participant kim = participants.getUserByName("kim");
        kim.receiveCard(Card.of(CardNumber.TEN, CardType.CLOVER));

        Participant park = participants.getUserByName("park");
        park.receiveCard(Card.of(CardNumber.FIVE, CardType.SPADE));

        ProfitResult dealerProfitResult = participants.calculateProfitResult().get(0);
        assertThat(dealerProfitResult.getProfit()).isEqualTo(-20000);
    }

    @Test
    @DisplayName("0승 2무 0패의 상황에서 최종 딜러의 수익을 계산한다.")
    public void calculateProfitResultDealerDrawTest() {
        Participants participants = makeParticipants();
        ProfitResult dealerProfitResult = participants.calculateProfitResult().get(0);
        assertThat(dealerProfitResult.getProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("0승 0무 2패, 한명의 유저가 블랙잭인 경우 최종 딜러의 수익을 계산한다.")
    public void calculateProfitResultDealerLoseBlackJackTest() {
        Participants participants = makeParticipants();
        Participant kim = participants.getUserByName("kim");
        kim.receiveCard(Card.of(CardNumber.TEN, CardType.CLOVER));
        kim.receiveCard(Card.of(CardNumber.ACE, CardType.SPADE));

        Participant park = participants.getUserByName("park");
        park.receiveCard(Card.of(CardNumber.FIVE, CardType.SPADE));

        ProfitResult dealerProfitResult = participants.calculateProfitResult().get(0);

        assertThat(dealerProfitResult.getProfit()).isEqualTo(-25000);
    }

    private Participants makeParticipants() {
        Participants participants = new Participants();
        Map<String, Integer> bettingPriceByName =
                Map.of("kim", 10000, "park", 10000);
        participants.addDealer();
        participants.addUsers(bettingPriceByName);
        return participants;
    }
}
