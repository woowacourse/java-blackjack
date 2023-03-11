package blackjack.domain;

import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    private Player player;

    @BeforeEach
    void setUpPlayer() {
        player = new Player("test", BETTING_MONEY_1000);
    }

    @DisplayName("전달받은 참가자에게 주어진 장수 만큼의 카드를 준다.")
    @Test
    void should_DrawCardsForPlayer_As_Count() {
        final List<Card> mockCards = List.of(new Card(SPADE, ACE), new Card(SPADE, TWO));
        final Deck deck = new MockDeckGenerator(mockCards).generate();

        deck.handCardsTo(player, 2);

        assertThat(player.cards())
                .containsExactly(new Card(SPADE, ACE), new Card(SPADE, TWO));
    }

    @DisplayName("전달받은 다수의 참가자에게 주어진 장수 만큼의 카드를 준다.")
    @Test
    void should_DrawCardsForPlayers_As_Count() {
        final List<Card> mockCards = List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                new Card(HEART, ACE), new Card(HEART, TWO)
        );
        final Deck deck = new MockDeckGenerator(mockCards).generate();
        final List<Player> players = List.of(player, new Player("test2", BETTING_MONEY_1000));

        deck.handCardsTo(players, 2);

        assertThat(player.cards())
                .containsExactly(new Card(SPADE, ACE), new Card(SPADE, TWO));
    }

    @DisplayName("카드 덱에 더 이상 카드가 없다면 예외를 발생한다.")
    @Test
    void should_ThrowException_When_DrawEmptyDeck() {
        final Deck deck = new Deck(new ArrayList<>());

        assertThatThrownBy(() -> deck.handCardsTo(player, 1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("더 이상 꺼낼 카드가 없습니다.");
    }
}
