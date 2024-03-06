package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    public static final int INITIAL_CARDS_COUNT = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Dealer dealer = new Dealer();
        List<String> playerNames = inputView.inputPlayerNames();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        Deck deck = Deck.createShuffledDeck();

        doInitialDraw(dealer, players, deck);

        outputView.printInitialMessage(playerNames);
        printInitialCards(dealer, players);
        for (Player player : players) {
            while (!player.isBusted()) {
                String drawChoice = inputView.inputDrawChoice(player.getName());
                if (drawChoice.equals("n")) {
                    break;
                }
                player.draw(deck);
                outputView.printPlayerCard(PlayerDto.from(player));
            }
        }

        dealer.drawUntilExceedMinimum(deck);
        printExtraDealerDraw(dealer);

        printResult(dealer, players);
    }

    private void printResult(Dealer dealer, List<Player> players) {
        PlayerResultDto dealerResult = PlayerResultDto.from(dealer.getPlayer());

        List<PlayerResultDto> playerResultDtos = players.stream()
                .map(PlayerResultDto::from)
                .toList();
        outputView.printResult(dealerResult, playerResultDtos);
    }

    private void printExtraDealerDraw(Dealer dealer) {
        int dealerCardsCount = dealer.getCardsCount();
        int extraDrawCount = dealerCardsCount - INITIAL_CARDS_COUNT;
        if (extraDrawCount > 0) {
            outputView.printExtraDealerDraw(extraDrawCount);
        }
    }

    private void printInitialCards(Dealer dealer, List<Player> players) {
        Card dealerCard = dealer.getFirstCard();
        outputView.printDealerInitialCard(dealerCard);

        List<PlayerDto> playerDtos = players.stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printPlayerInitialCards(playerDtos);
    }

    private static void doInitialDraw(Dealer dealer, List<Player> players, Deck deck) {
        for (Player player : players) {
            player.draw(deck);
            player.draw(deck);
        }

        dealer.draw(deck);
        dealer.draw(deck);
    }
}
