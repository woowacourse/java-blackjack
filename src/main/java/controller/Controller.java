package controller;

import domain.Answer;
import domain.rule.DealerRule;
import domain.rule.PlayerRule;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.*;

public class Controller {
    public void run() {
        List<Player> players = new ArrayList<>();
        Dealer dealer = new Dealer();
        List<String> names = Arrays.asList(InputView.requestName());
        validate(names);
        for (String name : names) {
            players.add(new Player(name));
        }
        OutputView.printDrawTurn(dealer.getName(), names);
        //---------
        CardDeck cardDeck = CardFactory.createCardDeck();
        dealer.draw(cardDeck.draw());
        dealer.draw(cardDeck.draw());
        OutputView.printStatus(dealer.getFirstStatus());
        for (Player player : players) {
            player.draw(cardDeck.draw());
            player.draw(cardDeck.draw());
            OutputView.printStatus(player.getStatus());
        }
        //---------
        for (Player player : players) {
            while (player.isDrawable(new PlayerRule())) {
                OutputView.printCardFormat(player.getName());
                Answer answer = InputView.askMoreCard();
                if (answer.isDisagree()) {
                    break;
                }
                player.draw(cardDeck.draw());
                OutputView.printStatus(player.getStatus());
            }
        }
        while (dealer.isDrawable(new DealerRule())) {
            OutputView.printAutoDraw(dealer);
            dealer.draw(cardDeck.draw());
        }
        //--------
        OutputView.printStatusWithScore(dealer.getStatus(), dealer.getScore());
        for (Player player : players) {
            OutputView.printStatusWithScore(player.getStatus(), player.getScore());
        }

        //------
        Map<String, Boolean> playerResult = new HashMap<>();
        Map<String, Integer> dealerResult = new HashMap<>();
        dealerResult.put(dealer.getName(),0);
        for (Player player : players) {
            playerResult.put(player.getName(),!dealer.isWinner(player));
            if(dealer.isWinner(player)) {
                dealerResult.put(dealer.getName(), dealerResult.get(dealer.getName())+1);
            }
        }
        OutputView.printGameResult(playerResult, dealerResult);
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
