package blackjack.controller;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.result.TotalResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PickStrategy pickStrategy;

    public BlackjackController(
            InputView inputView,
            OutputView outputView,
            PickStrategy pickStrategy
    ) {
        validate(inputView, outputView, pickStrategy);

        this.inputView = inputView;
        this.outputView = outputView;
        this.pickStrategy = pickStrategy;
    }

    private void validate(
            InputView inputView,
            OutputView outputView,
            PickStrategy pickStrategy
    ) {
        if (inputView == null) {
            throw new IllegalArgumentException("inputView가 null입니다.");
        }

        if (outputView == null) {
            throw new IllegalArgumentException("outputView가 null입니다.");
        }

        if (pickStrategy == null) {
            throw new IllegalArgumentException("pickStrategy가 null입니다.");
        }
    }

    public void run() {
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer();

        CardDeck cardDeck = CardDeck.of(pickStrategy);
        distributeInitialCards(dealer, players, cardDeck);

        players.forEach(
                player -> askHitOrStand(cardDeck, player)
        );

        players = players.stream()
                .filter(Participant::isBust)
                .map(Player::bust)
                .toList();

       drawUntilSeventeen(dealer, cardDeck);

        openDealerHands(dealer);
        openPlayersHands(players);

        printGameResult(players, dealer);
    }

    private List<Player> createPlayers() {
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        return names.stream()
                .map(name -> {
                    outputView.printBetAmountInputPrompt(name);
                    int amount = inputView.inputBetAmount();
                    return Player.of(name, amount);
                }).toList();
    }
    
    private void distributeInitialCards(
            Dealer dealer,
            List<Player> players,
            CardDeck cardDeck
    ) {
        dealer.pickInitialCards(cardDeck);
        players.forEach(
                player -> player.pickInitialCards(cardDeck)
        );

        outputView.printCardDistributionCompleted(players, dealer.getName());

        outputView.printParticipantCards(
                dealer.getName(),
                dealer.getOpenedCards()
        );
        players.forEach(
                player -> outputView.printParticipantCards(
                        player.getName(),
                        player.getOpenedCards()
                )
        );

        outputView.printNewLine();
    }

    private void askHitOrStand(
            CardDeck cardDeck,
            Player player
    ) {
        while (!player.isBust() && inputIsContinued(player)) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantCards(player.getName(), player.getAllCard());
        }
    }

    private boolean inputIsContinued(Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        return inputView.inputMoreCard();
    }

    private void drawUntilSeventeen(
            Dealer dealer,
            CardDeck cardDeck
    ) {
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
    }

    private void openDealerHands(Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCard(),
                dealer.getCurrentTotalScore()
        );
    }

    private void openPlayersHands(List<Player> players) {
        for (Player player : players) {
            outputView.printParticipantCardsWithScore(
                    player.getName(),
                    player.getAllCard(),
                    player.getCurrentTotalScore()
            );
        }
    }

    private void printGameResult(
            List<Player> players,
            Dealer dealer
    ) {
        outputView.printResult(
                TotalResult.of(players, dealer),
                dealer.getName()
        );
    }
}
