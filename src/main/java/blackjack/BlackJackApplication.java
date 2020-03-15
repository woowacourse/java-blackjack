package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    private static final int FIRST_TIME_DRAW_COUNT = 2;

    public static void main(String[] args) {
        try {
            startGame();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void startGame() {
        Participants participants = new Participants(InputView.inputNames());
        CardDeck cardDeck = new CardDeck();
        distributeFirstCards(participants, cardDeck);
        drawMoreCards(participants, cardDeck);
        printCalculatedResult(participants);
    }


    private static void printCalculatedResult(Participants participants) {
        GameResult gameResult = new GameResult(participants);
        OutputView.printUsersCardsAndScore(participants);
        OutputView.printFinalResult(participants, gameResult);
    }

    private static void distributeFirstCards(Participants participants, CardDeck cardDeck) {
        for (User participant : participants.getParticipants()) {
            participant.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        }
        OutputView.printCardDistribution(participants);
        OutputView.printUsersCards(participants);
    }

    private static void drawMoreCards(Participants participants, CardDeck cardDeck) {
        for (Player player : participants.getPlayers()) {
            drawMorePlayerCard(cardDeck, player);
        }
        Dealer dealer = participants.getDealer();
        while (dealer.canDrawCard()) {
            OutputView.printDealerOneMoreCard();
            dealer.drawCard(cardDeck);
        }
    }

    private static void drawMorePlayerCard(CardDeck cardDeck, Player player) {
        while (player.canDrawCard() && InputView.inputMoreCard(player)) {
            player.drawCard(cardDeck);
            OutputView.printPlayerCards(player);
        }
    }
}
