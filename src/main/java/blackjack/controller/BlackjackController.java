package blackjack.controller;

import blackjack.dto.PlayerResultDto;
import blackjack.dto.PlayerResultsDto;
import blackjack.model.carddeck.CardDeck;
import blackjack.model.carddeck.RandomPickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = setupPlayers();
        Dealer dealer = Dealer.create();
        CardDeck cardDeck = CardDeck.of(new RandomPickStrategy());

        askPlayersBetMoney(players);

        distributeInitCards(dealer, players, cardDeck);

        askPlayersHitOrStand(players, cardDeck);
        dealerHits(dealer, cardDeck);

        openDealerHands(dealer);
        openPlayersHands(players);

        printResult(players, dealer);
    }

    private void askPlayersBetMoney(final List<Player> players) {
        players.forEach(this::betMoney);
    }

    private void betMoney(final Player player) {
        outputView.printBettingAmountInputPlayer(player.getName());
        double amount = inputView.inputBettingAmount();
        player.bet(amount);
    }

    private List<Player> setupPlayers() {
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        return names.stream()
                .map(Player::of)
                .toList();
    }

    private void distributeInitCards(final Dealer dealer, final List<Player> players, final CardDeck cardDeck) {
        dealer.pickInitCards(cardDeck);
        players.forEach(player -> player.pickInitCards(cardDeck));

        outputView.printCardDistributionCompleted(players);

        outputView.printParticipantHands(dealer.getName(), dealer.getInitCards());
        players.forEach(player -> outputView.printParticipantHands(player.getName(), player.getInitCards()));
    }

    private void askPlayersHitOrStand(final List<Player> players, final CardDeck cardDeck) {
        players.forEach(player -> askHitOrStand(cardDeck, player));
    }

    private void askHitOrStand(final CardDeck cardDeck, final Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        boolean isContinued = inputView.inputMoreCard();

        while (isContinued) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantHands(player.getName(), player.getAllCards());
            outputView.printMoreCardInputPrompt(player.getName());
            isContinued = inputView.inputMoreCard();
        }
    }

    private void dealerHits(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();
    }

    private void openPlayersHands(final List<Player> players) {
        players.forEach(player -> outputView.printParticipantCardsWithScore(
                player.getName(),
                player.getAllCards(),
                player.getScore()
        ));
    }

    private void openDealerHands(final Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCards(),
                dealer.getScore()
        );
    }

    private void printResult(final List<Player> players, final Dealer dealer) {
        List<PlayerResultDto> results = players.stream()
                .map(player -> PlayerResultDto.from(player, player.getPlayerProfit(dealer)))
                .toList();

        outputView.printResult(new PlayerResultsDto(results));
    }
}
