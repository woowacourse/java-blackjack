package domain;

import static domain.constants.CardCommand.HIT;
import static domain.constants.CardCommand.STAND;

import controller.dto.GameResult;
import controller.dto.HandStatus;
import controller.dto.PlayerResult;
import domain.constants.CardCommand;
import domain.constants.Outcome;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private static final int THRESHOLD = 16;

    private final Players players;
    private final Deck deck;

    public BlackJackGame(final Players players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public static BlackJackGame from(final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        return new BlackJackGame(
                new Players(new Dealer(), players),
                new Deck()
        );
    }


    public List<HandStatus> initialize() {
        List<HandStatus> status = new ArrayList<>();

        // TODO: 모든 플레이어에 대한 다형성으로 처리할 수 있지 않을까? 동작이 모두 같잖아.
        for (Player player : players.getPlayers()) {
            player.pickTwoCards(deck);
            status.add(new HandStatus(player.getName(), player.getHand()));
        }
        return status;
    }

    public void giveCardToPlayer(final String name, final OutputView outputView, final InputView inputView) {
        Player player = getPlayer(name);
        HandStatus currentHand = new HandStatus(player.getName(), player.getHand());
        CardCommand command = inputCommand(name, inputView);
        while (HIT.equals(command)) {
            currentHand = createHandStatusAfterPick(player);
            outputView.printCardStatus(currentHand);
            if (player.cannotDraw()) {
                break;
            }
            command = inputCommand(name, inputView);
        }
        if (STAND.equals(command)) {
            outputView.printCardStatus(currentHand);
        }
    }

    private HandStatus createHandStatusAfterPick(final Player player) {
        player.pickOneCard(deck);
        return new HandStatus(player.getName(), player.getHand());
    }

    private CardCommand inputCommand(final String name, final InputView inputView) {
        return CardCommand.from(inputView.decideToGetMoreCard(name));
    }

    public void finish(final OutputView outputView) {
        List<Integer> scores = new ArrayList<>();
        scores.add(players.dealer().calculateResultScore());
        for (Player player : players.players()) {
            scores.add(player.calculateResultScore());
        }
        outputView.printResult(getCurrentHandStatus(), scores);
        outputView.printGameResult(getGameResult());
    }

    public List<HandStatus> getCurrentHandStatus() {
        List<HandStatus> handStatuses = new ArrayList<>();
        handStatuses.add(new HandStatus(players.dealer().getName(), players.dealer().getHand()));
        for (Player player : players.players()) {
            handStatuses.add(new HandStatus(player.getName(), player.getHand()));
        }
        return handStatuses;
    }

    public GameResult getGameResult() {
        GameRule rule = new GameRule(players);
        List<Outcome> results = rule.judge();
        List<String> names = getPlayerNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }

    public List<String> getPlayerNames() {
        List<Player> players = this.players.players();
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public void giveCardsToDealer(final OutputView outputView) {
        Dealer dealer = players.dealer();

        int currentScore = dealer.calculateResultScore();
        while (currentScore <= THRESHOLD) {
            dealer.pickOneCard(deck);
            outputView.printDealerPickMessage();
            currentScore = dealer.calculateResultScore();
        }
    }

    public Player getPlayer(final String name) {
        return players.players()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public Dealer getDealer() {
        return players.dealer();
    }
}
