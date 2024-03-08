package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.model.generator.CardGenerator;
import blackjack.model.generator.IndexGenerator;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("카드 두장을 지급한다.")
    @Test
    void distributeCards() {
        IndexGenerator indexGenerator = (maxRange) -> 2;
        CardGenerator cardGenerator = new CardGenerator(indexGenerator);

        Dealer dealer = new Dealer();
        List<Player> players = List.of(
                new Player("daon"),
                new Player("ella")
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

    @DisplayName("플레이어 카드상태를 업데이트 한다")
    @Test
    void update() {
        IndexGenerator indexGenerator = (maxRange) -> 2;
        CardGenerator cardGenerator = new CardGenerator(indexGenerator);

        Dealer dealer = new Dealer();
        Player player = new Player("daon");
        List<Player> players = List.of(
                player,
                new Player("ella")
        );
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, cardGenerator);

        blackJackGame.update(0);
        Player findPlayer = blackJackGame.getPlayers().get(0);

        assertThat(findPlayer.getCards().getCards()).hasSize(1);
    }
}


