package blackjack;

import blackjack.domain.Names;
import blackjack.domain.YesOrNo;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.result.ParticipantsResult;
import blackjack.domain.gambler.Participants;
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
        Participants participants = new Participants(new Names(InputView.inputNames()));
        CardDeck cardDeck = new CardDeck();
        distributeFirstCards(participants, cardDeck);
        drawMoreCards(participants, cardDeck);
        printCalculatedResult(participants);
    }

    private static void distributeFirstCards(Participants participants, CardDeck cardDeck) {
        participants.getDealer().drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        for (Player player : participants.getPlayers()) {
            player.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        }
        OutputView.printCardDistribution(participants);
        OutputView.printUsersCards(participants);
    }

    private static void drawMoreCards(Participants participants, CardDeck cardDeck) {
        for (Player player : participants.getPlayers()) {
            drawMorePlayerCardManual(cardDeck, player);
        }
        drawMoreDealerCardAuto(cardDeck, participants.getDealer());
    }

    private static void drawMorePlayerCardManual(CardDeck cardDeck, Player player) {
        while (player.canDrawCard() && YesOrNo.of(InputView.inputMoreCard(player)) == YesOrNo.YES) {
            player.drawCard(cardDeck);
            OutputView.printPlayerCards(player);
        }
    }

    private static void drawMoreDealerCardAuto(CardDeck cardDeck, Dealer dealer) {
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
