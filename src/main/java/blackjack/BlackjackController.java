package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.dto.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;
import java.util.function.Consumer;

public class BlackjackController {

    private static final int INIT_ROUND = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Player, Boolean> hitDecision = new HashMap<>();

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        run(Collections::shuffle);
    }

    public void run(final Consumer<List<Card>> shuffleStrategy) {
        final Deck deck = new Deck();
        deck.shuffle(shuffleStrategy);
        final Players players = createPlayers();
        final Dealer dealer = new Dealer();

        Map<Player, Money> wagers = placeWagers(players);

        dealInitialCards(deck, players, dealer);
        outputView.printInitialDeal(new InitialDealDto(players, dealer));

        processPlayersTurn(deck, players);
        processDealerTurn(deck, dealer);

        List<ParticipantFinalDto> finalCards = buildFinalCards(players, dealer);
        outputView.printFinalCards(finalCards);

        GameResults gameResults = GameResults.create(players, dealer);
        outputView.printFinalResults(new GameResultsDto(gameResults));

        Map<Participant, Money> profits = gameResults.calculateProfits(wagers, dealer);
        outputView.printProfits(ProfitsDto.from(profits));
    }

    private List<ParticipantFinalDto> buildFinalCards(Players players, Dealer dealer) {
        List<ParticipantFinalDto> finalCards = new ArrayList<>();
        finalCards.add(new ParticipantFinalDto(dealer));
        players.players().forEach(p -> finalCards.add(new ParticipantFinalDto(p)));
        return finalCards;
    }

    private Map<Player, Money> placeWagers(Players players) {
        Map<Player, Money> wagers = new LinkedHashMap<>();
        for (Player player : players.players()) {
            int amount = inputView.readWager(player);
            wagers.put(player, new Money(amount));
        }
        return wagers;
    }

    private Players createPlayers() {
        final List<String> names = inputView.readPlayerNames();

        final List<Player> playerList = names.stream()
                .map(Player::new)
                .toList();

        playerList.forEach(player -> hitDecision.put(player, true));

        return new Players(playerList);
    }

    private void dealInitialCards(final Deck deck, final Players players, final Dealer dealer) {
        for (int i = 0; i < INIT_ROUND; i++) {
            dealOneRound(deck, players, dealer);
        }
    }

    private void dealOneRound(final Deck deck, final Players players, final Dealer dealer) {
        players.players().forEach(player -> player.receiveCard(deck.draw()));
        dealer.receiveCard(deck.draw());
    }

    private void processPlayersTurn(final Deck deck, final Players players) {
        players.players().forEach(player -> processPlayerTurn(deck, player));
    }

    private void processPlayerTurn(final Deck deck, final Player player) {
        boolean hasHit = false;
        while (canProcess(player)) {
            final boolean hit = askHitAndProcess(deck, player);
            hasHit = hasHit || hit;
        }
        printCardsIfNeverHit(player, hasHit);
    }

    private boolean canProcess(Player player) {
        return hasHitDecision(player) && player.canReceiveCard();
    }

    private Boolean hasHitDecision(Player player) {
        return hitDecision.get(player);
    }

    private void printCardsIfNeverHit(final Player player, final boolean hasHit) {
        if (!hasHit) {
            outputView.printPlayerCards(new PlayerCardsDto(player));
        }
    }

    private boolean askHitAndProcess(final Deck deck, final Player player) {
        final boolean wantsHit = inputView.readHitDecision(player.getName());
        hitDecision.replace(player, wantsHit);
        if (!wantsHit) {
            return false;
        }
        player.receiveCard(deck.draw());
        outputView.printPlayerCards(new PlayerCardsDto(player));
        return true;
    }

    private void processDealerTurn(final Deck deck, final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.draw());
            outputView.printDealerHit();
        }
    }
}
