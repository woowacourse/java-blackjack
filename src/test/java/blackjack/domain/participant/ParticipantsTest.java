package blackjack.domain.participant;

import blackjack.domain.BlackJack;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantsTest {
    @Test
    @DisplayName("모든 플레이어에게 한 장씩 카드를 잘 나눠주는지")
    void Hand_Out_Card_To_All() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        Participants participants = blackJack.getParticipants();

        participants.handOutCardToAll(new CardDeck());

        assertThat(participants.getPlayers().stream()
            .allMatch(player -> player.getCards().size() == 1))
            .isEqualTo(true);
    }

    @Test
    @DisplayName("16 이하 일때 딜러가 한장의 카드를 더 받는지")
    void Dealer_Receive_Additional_Card_Under_16() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        Participants participants = blackJack.getParticipants();
        Participant dealer = participants.getDealer();

        dealer.receiveCard(new Card("8스페이드", 8));
        dealer.receiveCard(new Card("5하트", 5));

        assertThat(participants.isDealerNeedAdditionalCard()).isEqualTo(true);
    }

    @Test
    @DisplayName("17 이하 일때 딜러가 카드를 더 받지 않는지")
    void Dealer_NOT_Receive_Additional_Card_OVER_17() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        Participants participants = blackJack.getParticipants();
        Participant dealer = participants.getDealer();

        dealer.receiveCard(new Card("8스페이드", 8));
        dealer.receiveCard(new Card("J하트", 10));
        
        assertThat(participants.isDealerNeedAdditionalCard()).isEqualTo(false);
    }
}
