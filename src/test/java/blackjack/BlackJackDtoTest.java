package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.card.Card;
import blackjack.domain.dto.BlackJackDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BlackJackDtoTest {

    @Test
    @DisplayName("딜러의 카드 중 1장만을 오픈하는지")
    void Open_Only_One_Dealer_Card() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        blackJack.handOutStartingCards();
        BlackJackDto blackJackDto = BlackJackDto.from(blackJack);
        assertThat(blackJackDto.getDealerOpenCard()).isEqualTo(blackJackDto.getDealer().getName() + ": " + blackJackDto.getDealer().getCards().get(0).getName());
    }

    @Test
    @DisplayName("참가자의 현재 카드 상태를 반환하는지")
    void Return_Player_Current_Cards_Status() {
        List<String> playerNames = Arrays.asList("a", "b");
        BlackJack blackJack = BlackJack.createFrom(playerNames);
        blackJack.handOutStartingCards();
        BlackJackDto blackJackDto = BlackJackDto.from(blackJack);
        Participant player = blackJackDto.getPlayers().get(0);
        String[] playerCardStatus = player.getCards().stream().map(Card::getName).toArray(String[]::new);
        assertThat(blackJackDto.getPlayerCardStatus(player)).isEqualTo(player.getName() + ": " + String.join(", ", playerCardStatus));
    }
}
