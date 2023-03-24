package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.DealerScore;
import domain.result.Judge;
import domain.result.PlayerScore;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardGame {

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(Name.generateDealerName(), generateInitCards(cardDeck));
        Players players = makePlayers(cardDeck);
        play(cardDeck, dealer, players);
        end(dealer, players);
    }

    private Cards generateInitCards(CardDeck cardDeck) {
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
        return names.stream()
                .map(InputView::readMoney)
                .collect(Collectors.toList());
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
