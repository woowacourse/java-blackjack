package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @DisplayName("카드 두장을 지급한다")
    @Test
    void distributeCards() {
        Dealer dealer = new Dealer();
        List<Player> players = List.of(
                new Player("daon"),
                new Player("ella")
        );
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);

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
        Dealer dealer = new Dealer();
        List<Player> players = List.of(new Player("daon"));
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);

        Player findPlayer = blackJackGame.getPlayers().get(0);
        List<Card> initialCards = List.of(
                new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.CLOVER));
        findPlayer.addCards(initialCards);

        blackJackGame.update(0);

        assertThat(findPlayer.getCards().getCards()).hasSize(3);
    }
}
