package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("Players 객체 생성 테스트")
    public void createPlayersTest() {
        Gambler gambler = Gambler.of(Name.of("test"));
        Gamblers gamblers = Gamblers.of(List.of(gambler));
        assertThat(gamblers.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Players 카드 배분 테스트")
    public void giveCardToPlayersTest() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Gambler gambler1 = Gambler.of(Name.of("pobi"));
        Gambler gambler2 = Gambler.of(Name.of("jason"));
        Gamblers gamblers = Gamblers.of(List.of(gambler1, gambler2));
        gamblers.distributeCard(cardDeck);
        gamblers.distributeCard(cardDeck);
        assertThat(gambler1.getCards().size()).isEqualTo(2);
        assertThat(gambler2.getCards().size()).isEqualTo(2);
    }
}
