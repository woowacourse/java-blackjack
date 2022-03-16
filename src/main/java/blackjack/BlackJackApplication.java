package blackjack;

import static blackjack.controller.BlackJackController.createPlayers;
import static blackjack.controller.BlackJackController.initiateParticipantsHand;
import static blackjack.controller.BlackJackController.receiveBettingMoney;
import static blackjack.controller.BlackJackController.takeMoreCard;

import blackjack.domain.BettingMoney;
import blackjack.domain.ScoreBoard;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.RandomCardsGenerateStrategy;
import blackjack.domain.dto.ScoreBoardResponse;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackApplication {

    public static void main(final String... args) {
        Deck deck = new Deck(new RandomCardsGenerateStrategy());
        Dealer dealer = new Dealer();

        runGame(deck, dealer);
    }

    private static void runGame(Deck deck, Dealer dealer) {
        List<Player> players = createPlayers();
        List<BettingMoney> bettingMonies = receiveBettingMoney(players);
        initiateParticipantsHand(dealer, players, deck);
        takeMoreCard(players, dealer, deck);
        OutputView.printParticipantScore(dealer, players);

        ScoreBoard scoreBoard = ScoreBoard.of(dealer, players);
        ScoreBoardResponse scoreBoardResponse = ScoreBoardResponse.from(scoreBoard);
        OutputView.printMatchResult(scoreBoardResponse);
    }
}
