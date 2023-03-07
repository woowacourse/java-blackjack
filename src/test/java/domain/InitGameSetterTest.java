package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Deck;
import domain.card.DeckGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitGameSetterTest {

    @Test
    @DisplayName("플레이어들을 생성한다.")
    void generatePlayers() {
        DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = deckGenerator.generate();
        List<String> playerNames = List.of("seongha", "dino");

        Assertions.assertDoesNotThrow(() -> InitGameSetter.generatePlayers(deck, playerNames));
    }

    @Test
    @DisplayName("딜러를 생성한다.")
    void generateDealer() {
        DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = deckGenerator.generate();

        Assertions.assertDoesNotThrow(() -> InitGameSetter.generateDealer(deck));
    }
}
