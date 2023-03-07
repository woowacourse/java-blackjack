package controller;

import domain.BlackjackGame;
import domain.CardNumber;
import domain.Deck;
import domain.PlayerResultRepository;
import domain.Symbol;
import domain.user.Player;
import java.util.Arrays;
import java.util.stream.Collectors;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private static final String HIT = "y";
    private static final String STAND = "n";
    private final PlayerResultRepository playerResultRepository;
    private final Deck deck;

    public BlackjackController() {
        this.playerResultRepository = new PlayerResultRepository();
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
    }

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.readPlayersName());
        blackjackGame.initGame(this.deck);
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.getPlayers().forEach(this::giveCardUntilImpossible);
        addCardToDealerIfPossible(blackjackGame);
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.calculateAllResults(playerResultRepository);
        OutputView.printResults(playerResultRepository.getRepository());
    }

    private void addCardToDealerIfPossible(BlackjackGame blackjackGame) {
        if (blackjackGame.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            blackjackGame.addCardToDealerIfPossible(this.deck);
        }
    }

    private void giveCardUntilImpossible(Player player) {
        String whetherDrawCard = HIT;
        while (player.canAdd() && (whetherDrawCard = InputView.readWhetherDrawCardOrNot(player)).equals(HIT)){
            player.addCard(this.deck.draw());
            OutputView.printCardsStatusOfUser(player);
        }
        if (STAND.equals(whetherDrawCard)) {
            OutputView.printCardsStatusOfUser(player);
        }
    }
}
