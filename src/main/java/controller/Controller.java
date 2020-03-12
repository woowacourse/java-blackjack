package controller;

import domain.Answer;
import domain.GameResult;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.rule.DealerRule;
import domain.rule.PlayerRule;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.*;

public class Controller {
    private CardDeck cardDeck = CardFactory.createCardDeck();

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = createPlayers();

        drawFirstCards(dealer, players);
        for (Player player : players) {
            drawMoreCards(player);
        }
        drawMoreCards(dealer);

        passResult(dealer, players);
    }

    private void drawMoreCards(Dealer dealer) {
        while (dealer.isDrawable(new DealerRule())) {
            OutputView.printAutoDraw(dealer);
            dealer.draw(cardDeck.draw());
        }
    }

    private void drawMoreCards(Player player) {
        while (player.isDrawable(new PlayerRule()) && askPlayerDraw(player)) {
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
    }

    private boolean askPlayerDraw(Player player) {
        OutputView.printCardFormat(player.getName());
        Answer answer = InputView.askMoreCard();
        return answer.isAgree();
    }

    private void passResult(Dealer dealer, List<Player> players) {
        OutputView.printStatusWithScore(dealer.getStatus(), dealer.getScore());
        for (Player player : players) {
            OutputView.printStatusWithScore(player.getStatus(), player.getScore());
        }
        OutputView.printGameResult(new GameResult(players, dealer), dealer);
    }

    private void drawFirstCards(Dealer dealer, List<Player> players) {
        OutputView.printDrawTurn(dealer, players);
        dealer.draw(cardDeck.draw());
        dealer.draw(cardDeck.draw());
        OutputView.printStatus(dealer.getFirstStatus());
        for (Player player : players) {
            player.draw(cardDeck.draw());
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        List<String> names = Arrays.asList(InputView.requestName());

        validate(names);
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    private void validate(List<String> names) {
        validateNull(names);
        validateEmptyName(names);
        validateDuplication(names);
    }

    private void validateNull(List<String> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
    }

    private void validateEmptyName(List<String> names) {
        boolean hasEmptyName = names.stream()
                .map(String::isEmpty)
                .findAny()
                .orElse(false);
        if (hasEmptyName) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> playerNames = new HashSet<>(names);
        if (playerNames.size() != names.size()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
    }
}
