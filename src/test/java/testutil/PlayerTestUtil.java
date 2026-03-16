package testutil;

import domain.Card;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.enums.CardRank;
import domain.enums.CardShape;
import utils.generator.CardsGenerator;
import utils.generator.ShuffledCardsGenerator;

import java.util.ArrayList;
import java.util.List;

public final class PlayerTestUtil {
    private static final String DEALER = "딜러";
    private static final String PLAYER_NAME_1 = "아티";
    private static final String PLAYER_NAME_2 = "요크";

    public static Player createPlayer(String name, List<Card> cards) {
        Player player = new Player(name);
        cards.forEach(player::add);
        return player;
    }

    public static Players createPlayers() {
        Player player1 = createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

        Player player2 = createPlayer(
                PLAYER_NAME_2,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

        return new Players(List.of(player1, player2));
    }

    public static Player createBurstPlayer() {
        return createPlayer(
                PLAYER_NAME_1,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK),
                        new Card(CardShape.SPADE, CardRank.NINE)
                )); // 29

    }

    public static Player createNonBurstPlayer() {
        return createPlayer(
                PLAYER_NAME_2,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

    }

    public static Player createBlackjackPlayer() {
        return createPlayer(
                PLAYER_NAME_2,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

    }

    public static Player createBurstDealer() {
        return createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK),
                        new Card(CardShape.SPADE, CardRank.NINE)
                )); // 29

    }

    public static Player createNonBurstDealer() {
        return createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.JACK)
                )); // 20

    }

    public static Player createBlackjackDealer() {
        return createPlayer(
                DEALER,
                List.of(
                        new Card(CardShape.SPADE, CardRank.TEN),
                        new Card(CardShape.SPADE, CardRank.ACE)
                )); // 21

    }

    public static Deck createDeck() {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        return new Deck(cardsGenerator);
    }

    public static class FakeShuffledCardsGenerator implements CardsGenerator {
        @Override
        public List<Card> generateShuffledCards() {
            return new ArrayList<>(
                    List.of(new Card(CardShape.SPADE, CardRank.ACE),
                    new Card(CardShape.SPADE, CardRank.TWO),
                    new Card(CardShape.SPADE, CardRank.THREE),
                    new Card(CardShape.SPADE, CardRank.FOUR),
                    new Card(CardShape.SPADE, CardRank.FIVE))
            );
        }
    }
}
