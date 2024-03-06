package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.BlackJackGame;
import blackjack.model.Card;
import blackjack.model.CardGenerator;
import blackjack.model.Cards;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.generator.IndexGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("카드 두장을 지급한다.")
    @Test
    void distributeCards() {
        IndexGenerator indexGenerator = (maxRange) -> 2;
        CardGenerator cardGenerator = new CardGenerator(indexGenerator);

        Dealer dealer = new Dealer(new Cards());
        List<Player> players = List.of(
                new Player("daon", new Cards()),
                new Player("ella", new Cards())
        );

        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, cardGenerator);

        blackJackGame.distributeCards();
        List<Card> dealerCards = dealer.getCards().getCards();
        List<List<Card>> playerCards = players.stream()
                .map(Player::getCards)
                .map(Cards::getCards)
                .toList();

        assertAll(
                () -> assertThat(dealerCards).hasSize(2),
                () -> assertThat(playerCards.get(0)).hasSize(2),
                () -> assertThat(playerCards.get(1)).hasSize(2)
        );
    }
}
