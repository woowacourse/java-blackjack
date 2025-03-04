package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardGenerator;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    private InputVIew inputView;
    private OutputView outputView;
    private final CardGenerator cardGenerator;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardGenerator = new CardGenerator();
    }

    public void run() {
        String[] playerNames = inputView.readPlayerName();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            CardDeck cardDeck = new CardDeck();
            cardDeck.add(cardGenerator.generate());
            cardDeck.add(cardGenerator.generate());

            Player player = new Player(playerName, cardDeck, cardGenerator);
            players.add(player);
        }

        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardGenerator.generate());
        cardDeck.add(cardGenerator.generate());
        Dealer dealer = new Dealer(cardDeck, cardGenerator);

        outputView.displayDistributedCardStatus(dealer, players);

    }
}
