package blackjack.domain.participant;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.result.DistributeResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
    public void getUserResultTest() {
        Participants participants = new Participants();
        participants.addDealer();
        participants.addUsers(new String[]{"pokemon", "yugioh"});

        assertThat(participants.getUsers().size()).isEqualTo(2);
    }
}
