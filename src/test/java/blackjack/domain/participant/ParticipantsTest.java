package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class ParticipantsTest {

    private final List<Player> participantGroup = Arrays.asList(new Player("jason"), new Player("kevin"));
    private final Dealer dealer = new Dealer();

    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    @Test
    public void validateOverlappedNames() {
        List<Player> duplicatedPlayers = Arrays.asList(new Player("jason"), new Player("jason"));

        assertThatCode(() -> {
            Participants.of(dealer, duplicatedPlayers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }

    @DisplayName("플레이어들이 카드를 2장씩 받는다.")
    @Test
    public void receiveDefaultCards() {
        CardDeck cardDeck = new CardDeck();
        Participants participants = Participants.of(dealer, participantGroup);
        Participant jason = participants.toList()
                .get(0);

        List<Card> jasonCards = jason.getCards();
        participants.receiveDefaultCards(cardDeck);
        int afterSize = jasonCards.size();

        assertThat(afterSize).isEqualTo(2);
    }
}
