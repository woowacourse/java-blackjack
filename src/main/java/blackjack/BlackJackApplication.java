package blackjack;

import static blackjack.controller.BlackJackController.createPlayers;
import static blackjack.controller.BlackJackController.initiateParticipantsHand;
import static blackjack.controller.BlackJackController.placeBetting;
import static blackjack.controller.BlackJackController.printBettingReturn;
import static blackjack.controller.BlackJackController.printParticipantsHand;
import static blackjack.controller.BlackJackController.takeMoreCard;

import blackjack.domain.BettingReturn;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.RandomCardsGenerateStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.BettingReturnResponse;
import java.util.List;

public class BlackJackApplication {

    public static void main(final String... args) {
        Deck deck = new Deck(new RandomCardsGenerateStrategy());
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers();
        placeBetting(players);

        runGame(deck, dealer, players);
    }

    private static void runGame(Deck deck, Dealer dealer, List<Player> players) {
        initiateParticipantsHand(dealer, players, deck);
        printParticipantsHand(dealer, players);
        takeMoreCard(players, dealer, deck);

        BettingReturn bettingReturn = BettingReturn.of(dealer, players);
        BettingReturnResponse bettingReturnResponse = BettingReturnResponse.from(bettingReturn);
        printBettingReturn(bettingReturnResponse);
    }
}
