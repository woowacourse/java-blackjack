package blackjack.testutil;

import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

import java.util.List;

public class ParticipantGenerator {
    public static Dealer createDealer(List<Number> numbers) {
        CustomDeck customDeck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(customDeck);
        Dealer dealer = new Dealer(handGenerator);
        CardDrawer.addAllCards(customDeck, dealer);
        return dealer;
    }

    public static Player createPlayer(List<Number> numbers) {
        CustomDeck customDeck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(customDeck);
        Player player = new Player(new Name("감자"), handGenerator);
        CardDrawer.addAllCards(customDeck, player);
        return player;
    }
}
