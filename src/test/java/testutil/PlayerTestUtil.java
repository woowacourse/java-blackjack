package testutil;

import domain.Card;
import domain.Player;
import domain.enums.CardRank;
import domain.enums.CardShape;
import utils.generator.CardsGenerator;

import java.util.ArrayList;
import java.util.List;

public final class PlayerTestUtil {
    public static Player createPlayer(String name, List<Card> cards) {
        Player player = new Player(name);
        cards.forEach(player::add);
        return player;
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
