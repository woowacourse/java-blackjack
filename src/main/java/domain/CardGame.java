package domain;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class CardGame {

    public void run() {
        CardDeck cardDeck = CardDeck.generateCardDeck();
        Dealer dealer = new Dealer(Name.generateDealerName(), generateInitCards(cardDeck));
        Players players = makePlayers(cardDeck);
        play(cardDeck, dealer, players);
        end(dealer, players);
    }

    private static Cards generateInitCards(CardDeck cardDeck) {
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.pickCard());
        cards.add(cardDeck.pickCard());
        return new Cards(cards);
    }

    private Players makePlayers(CardDeck cardDeck) {
        List<String> names = InputView.readNames();
        List<Integer> playersMoney = getPlayersMoney(names);
        return Players.of(names, cardDeck, playersMoney);
    }

    private List<Integer> getPlayersMoney(List<String> names) {
        List<Integer> playersMoney = new ArrayList<>();
        for (String name : names) {
            playersMoney.add(InputView.readMoney(name));
        }
        return playersMoney;
    }

    private void play(CardDeck cardDeck, Dealer dealer, Players players) {
        OutputView.printStart(dealer, players);
        for (Player player : players.getPlayers()) {
            draw(cardDeck, player);
        }

        while (dealer.canReceiveOneMoreCard()) {
            dealer.pickCard(cardDeck);
            OutputView.printHit();
        }
    }

    private void draw(CardDeck cardDeck, Player player) {
        boolean isContinue = false;
        while (player.canReceiveOneMoreCard() && (isContinue = InputView.readYesOrNo(player.getName()))) {
            player.pickCard(cardDeck);
            OutputView.printCard(player);

        }
        if (isContinue) {
            return;
        }
        OutputView.printCard(player);
    }

    private void end(Dealer dealer, Players players) {
        OutputView.printResults(dealer, players);
        List<PlayerScore> playerScores = judgePlayerScores(dealer, players);
        DealerScore dealerScore = DealerScore.generateDealerScore(playerScores);
        OutputView.printProfits(dealerScore, playerScores);
    }

    private List<PlayerScore> judgePlayerScores(Dealer dealer, Players players) {
        List<PlayerScore> playerScores = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playerScores.add(Judge.judgeScore(player, dealer));
        }
        return playerScores;
    }
}
