package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.DeckInitializer;
import blackjack.model.Participant;
import blackjack.model.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackJackController(InputView inputView, OutputView outputView, DeckInitializer deckInitializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        deck = deckInitializer.generateDeck();
    }

    public void run() {
        List<Participant> participants = Parser.parseParticipants(inputView.inputParticipant());
        Dealer dealer = new Dealer();
        giveInitialCards(dealer);
        for (Participant participant : participants) {
            giveInitialCards(participant);
        }
        outputView.outputFirstCardDistributionResult(participants, dealer);


    }

    public void giveInitialCards(Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }


}
