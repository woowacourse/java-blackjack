package blackjack.controller;

import static blackjack.view.InputView.requestMorePlayerCardInput;
import static blackjack.view.OutputView.printPlayerCardsInfo;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Player;
import java.util.List;

public class BlackjackController {
    public BlackjackGame initializeGame(List<String> playerNames) {
        return new BlackjackGame(new CardDeck(), playerNames);
    }

    public void givePlayerCards(Player player, BlackjackGame game) {
        while (player.canReceive()) {
            if (!requestMorePlayerCardInput(player.getName())) {
                return;
            }
            player.receiveCard(game.popCard());
            printPlayerCardsInfo(player);
        }
    }
}
