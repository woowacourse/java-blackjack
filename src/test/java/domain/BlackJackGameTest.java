package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        PlayerNames playerNames = new PlayerNames(names);

        CardDeck cardDeck = new CardDeck();

        assertThatCode(() -> new BlackJackGame(playerNames, cardDeck))
                .doesNotThrowAnyException();
    }

    @DisplayName("게임에서 참가자에게 카드를 Hit한다.")
    @Test
    void hitPlayer() {
        List<String> names = List.of("산초", "위브");
        PlayerNames playerNames = new PlayerNames(names);
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        Card card1 = new Card(Letter.SEVEN, Mark.CLOVER);
        Card card2 = new Card(Letter.SEVEN, Mark.SPADE);
        Card card3 = new Card(Letter.SEVEN, Mark.HEART);
        List<Card> cards = new ArrayList<>(List.of(card1, card2, card3));
        Hand hand = new Hand(cards);
        Name name = new Name("산초");
        Player player = new Player(name, hand);

        assertThatCode(() -> blackJackGame.hitPlayer(player))
                .doesNotThrowAnyException();
    }

    @DisplayName("게임에서 딜러에게 카드를 hit한다.")
    @Test
    void hitDealer() {
        List<String> names = List.of("산초", "위브");
        PlayerNames playerNames = new PlayerNames(names);
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        assertThatCode(() -> blackJackGame.hitDealer())
                .doesNotThrowAnyException();
    }
}
