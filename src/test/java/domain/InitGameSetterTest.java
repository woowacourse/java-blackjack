package domain;

import java.util.List;

import domain.card.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitGameSetterTest {

    @Test
    @DisplayName("플레이어들을 생성한다.")
    void generatePlayers() {
        Deck deck = new Deck();
        List<String> playerNames = List.of("seongha", "dino");

        Assertions.assertDoesNotThrow(() -> InitGameSetter.generatePlayers(deck, playerNames));
    }

    @Test
    @DisplayName("딜러를 생성한다.")
    void generateDealer() {
        Deck deck = new Deck();

        Assertions.assertDoesNotThrow(() -> InitGameSetter.generateDealer(deck));
    }
}
