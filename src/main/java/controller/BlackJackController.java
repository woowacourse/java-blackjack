package controller;

import domain.Answer;
import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import dto.DealerHandsDto;
import dto.PlayerHandsDto;
import domain.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = Players.from(inputView.readPlayerNames());
        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(cardDeck);

        dealer.startDeal(players);
        outputView.printStartDeal(DealerHandsDto.from(dealer), PlayerHandsDto.from(players));

        for (Player player : players.getNames()) {
            dealer.deal(player, Answer.from(inputView.readAnswer(player.getName())));
        }
    }
}
