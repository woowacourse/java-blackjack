package blackjack.controller;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.participant.Dealer;
import blackjack.model.cardDeck.PickStrategy;
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
        List<Player> players = initPlayers();
        Dealer dealer = new Dealer();

        CardDeck cardDeck = CardDeck.of(pickStrategy);
        distributeInitialCards(dealer, players, cardDeck);

        players.forEach(
                player -> askHitOrStand(cardDeck, player)
        );

        if (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }

        openDealerHands(dealer);
        openPlayersHands(players);

        printGameResult(players, dealer);
    }

    private List<Player> initPlayers() {
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        return names.stream()
                .map(Player::of).toList();
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

        outputView.printCardDistributionCompleted(players);

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
    }

    private void askHitOrStand(
            CardDeck cardDeck,
            Player player
    ) {
        while (player.canPick() && inputIsContinued(player)) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantCards(player.getName(), player.getAllCard());
        }

    }

    private boolean inputIsContinued(Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        return inputView.inputMoreCard();
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
                TotalResult.of(players, dealer)
        );
    }
}
