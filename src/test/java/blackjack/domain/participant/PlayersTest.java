package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardsGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PlayersTest {

    private final List<Player> playerList = Arrays.asList(new Player("jason"));

    @DisplayName("플레이어는 최소 1명 최대 7명은 있어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 8})
    void cannotMakePlayers(int playerCounts) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCounts; i++) {
            players.add(new Player("test" + i));
        }

        assertThatCode(() -> {
            new Players(players);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의 수는 딜러 제외 최소 1명 최대 7명입니다.");
    }

    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    @Test
    public void validateOverlappedNames() {
        List<Player> duplicatedPlayers = Arrays.asList(new Player("jason"), new Player("jason"));

        assertThatCode(() -> {
            new Players(duplicatedPlayers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }

    @DisplayName("플레이어들이 카드를 2장씩 받는다.")
    @Test
    public void receiveDefaultCards() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateCards());
        Players players = new Players(playerList);
        Participant jason = players.toList()
                .get(0);

        List<Card> jasonCards = jason.getCards();
        players.receiveDefaultCards(cardDeck);

        assertThat(jasonCards).hasSize(2);
    }
}
