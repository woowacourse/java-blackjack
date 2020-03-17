package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.ParticipantsResult;
import blackjack.domain.user.Participants;
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

    private static void distributeFirstCards(Participants participants, CardDeck cardDeck) {
        for (User participant : participants.getParticipants()) {
            participant.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        }
        OutputView.printCardDistribution(participants);
        OutputView.printUsersCards(participants);
    }

    private static void drawMoreCards(Participants participants, CardDeck cardDeck) {
        for (User player : participants.getPlayers()) {
            drawMorePlayerCardManual(cardDeck, player);
        }
        drawMoreDealerCardAuto(cardDeck, participants.getDealer());
    }

    private static void drawMorePlayerCardManual(CardDeck cardDeck, User player) {
        while (player.canDrawCard() && InputView.inputMoreCard(player)) {
            player.drawCard(cardDeck);
            OutputView.printPlayerCards(player);
        }
    }

    private static void drawMoreDealerCardAuto(CardDeck cardDeck, User dealer) {
        while (dealer.canDrawCard()) {
            OutputView.printDealerOneMoreCard(dealer);
            dealer.drawCard(cardDeck);
        }
    }

    private static void printCalculatedResult(Participants participants) {
        ParticipantsResult participantsResult = new ParticipantsResult(participants);
        OutputView.printUsersCardsAndScore(participants);
        OutputView.printFinalResult(participants, participantsResult);
    }
}
