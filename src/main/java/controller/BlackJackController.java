package controller;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Nickname;
import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        final String inputNicknames = InputView.readPlayerName();
        final List<Nickname> nicknames = Arrays.stream(inputNicknames.split(",")).map(Nickname::new).toList();

        DeckGenerator deckGenerator = new DeckGenerator();
        final List<Card> cards = deckGenerator.generate();
        deckGenerator.shuffle(cards, new Random());
        final Deck deck = new Deck(cards);

        final List<Player> players = nicknames.stream()
                .map(Player::new)
                .toList();

        players.forEach(player -> {
            final Card firstCard = deck.drawCard();
            final Card secondCard = deck.drawCard();
            player.receiveInitialCards(List.of(firstCard, secondCard));
        });

        final Dealer dealer = new Dealer(new Nickname("딜러"));

        dealer.receiveInitialCards(List.of(deck.drawCard(), deck.drawCard()));

        OutputView.printInitialSettingMessage("딜러", nicknames, 2);

        OutputView.printCardsInHand(dealer.getDisplayName(), dealer.getCards());

        players.forEach(player -> OutputView.printCardsInHand(player.getDisplayName(), player.getCards()));

        for (Player player : players) {
            String input = InputView.readQuestOneMoreCard(player.getDisplayName());
            if (input.equals("y")) {
                final Card card = deck.drawCard();
                player.hit(card);

            }
            OutputView.printCardsInHand(player.getDisplayName(), player.getCards());
            if (player.isBust()) {
                OutputView.printBustMessage(player.getDisplayName());
                continue;
            }

            if (input.equals("n")) {
                continue;
            }
            while (InputView.readQuestOneMoreCard(player.getDisplayName()).equals("y")) {
                final Card card = deck.drawCard();
                player.hit(card);
                OutputView.printCardsInHand(player.getDisplayName(), player.getCards());
                if (player.isBust()) {
                    OutputView.printBustMessage(player.getDisplayName());
                    break;
                }
            }
        }

    }
}
