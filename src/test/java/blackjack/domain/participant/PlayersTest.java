package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어들 객체 생성자 테스트")
    @Test
    void create() {
        CardDeck cardDeck = CardDeck.initShuffled();
        Players players = new Players(List.of(new Player(
                new Name("pobi"),
                new Cards(cardDeck.distribute(2)))));

        assertThat(players).isNotNull();
    }

}
