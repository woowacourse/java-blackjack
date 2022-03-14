package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import domain.card.Number;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    private List<Name> names;
    private BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        names = Arrays.asList(new Name("pobi"), new Name("jason"));
        blackJackGame = new BlackJackGame(names);
    }

    @Test
    @DisplayName("게임 초기화 시 각 플레이어는 2장의 카드를 분배받는다.")
    void create() {
        // when
        boolean match = blackJackGame
                .findPlayers()
                .stream()
                .mapToInt(this::calculateCardsSize)
                .anyMatch(cardSize -> cardSize != 2);

        // then
        assertThat(match).isFalse();
    }

    private int calculateCardsSize(Participant participant) {
        return participant
                .getCards()
                .getValue()
                .size();
    }

    @Test
    @DisplayName("딜러 가져오기")
    void find_dealer() {
        // then
        assertThat(blackJackGame.findDealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("플레이어들 가져오기")
    void find_players() {
        // given
        List<Participant> expectPlayers = new ArrayList<>();
        for (Name name : names) {
            expectPlayers.add(new Player(name, getCards(Number.SEVEN, Number.QUEEN)));
        }

        // when
        List<Participant> playerNames = blackJackGame.findPlayers();

        // then
        assertThat(playerNames).containsAll(expectPlayers);
    }
}
