package domain;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardDeckMaker;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    private CardDistributor distributor;

    @BeforeEach
    void setUp() {
        distributor = new CardDistributor(CardDeckMaker.generate());
    }

    @Test
    @DisplayName("참여자 이름은 중복을 허용하지 않는다.")
    void createPlayersDuplicateFail() {
        List<String> playerNames = List.of("a", "a", "a");

        assertThatThrownBy(() -> Players.of(playerNames, distributeCardDecks(3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자 이름은 중복될 수 없습니다.");
    }

    private List<CardDeck> distributeCardDecks(int count) {
        List<CardDeck> cardDecks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cardDecks.add(new CardDeck(distributor.distributeInitialCard()));
        }
        return cardDecks;
    }

}
