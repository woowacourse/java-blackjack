package blackjack;

import static blackjack.controller.BlackJackController.createPlayers;
import static blackjack.controller.BlackJackController.initiateParticipantsHand;
import static blackjack.controller.BlackJackController.printParticipantsHand;
import static blackjack.controller.BlackJackController.printRevenueResultResponse;
import static blackjack.controller.BlackJackController.receiveBettingMoney;
import static blackjack.controller.BlackJackController.takeMoreCard;

import blackjack.domain.BettingMoney;
import blackjack.domain.BettingReturn;
import blackjack.domain.ScoreBoard;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.RandomCardsGenerateStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.RevenueResultResponse;
import java.util.List;

public class BlackJackApplication {

    public static void main(final String... args) {
        Deck deck = new Deck(new RandomCardsGenerateStrategy());
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers();
        List<BettingMoney> bettingMonies = receiveBettingMoney(players);

        runGame(deck, dealer, players, bettingMonies);
    }

    private static void runGame(Deck deck, Dealer dealer, List<Player> players, List<BettingMoney> bettingMonies) {
        initiateParticipantsHand(dealer, players, deck);
        printParticipantsHand(dealer, players);
        takeMoreCard(players, dealer, deck);

        ScoreBoard scoreBoard = ScoreBoard.of(dealer, players);
        BettingReturn bettingReturn = BettingReturn.of(scoreBoard, bettingMonies);
        RevenueResultResponse revenueResultResponse = RevenueResultResponse.from(bettingReturn);
        printRevenueResultResponse(revenueResultResponse);
    }
}
