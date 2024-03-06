package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Deck deck = Deck.createShuffledDeck();

        List<String> playerNames = inputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        for (Player player : players) {
            player.draw(deck);
            player.draw(deck);
        }

        Dealer dealer = new Dealer();
        dealer.draw(deck);
        dealer.draw(deck);

        outputView.printInitialMessage(playerNames);

        Card dealerCard = dealer.getFirstCard();
        outputView.printDealerInitialCard(dealerCard);

        List<PlayerDto> playerDtos = players.stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printPlayerInitialCards(playerDtos);
    }
}
