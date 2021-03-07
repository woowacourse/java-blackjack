package blackjack.controller;

import blackjack.domain.MatchResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;

public class BlackJackController {
    private static final String YES = "Y";

    public void run() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck(Card.values());
        Players players = getPlayerNames();
        playGame(dealer, deck, players);
        showResult(dealer, players);
    }

    private Players getPlayerNames() {
        OutputView.printPlayerNameInputGuideMessage();
        try {
            return new Players(InputView.getPlayerNameInput());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private void playGame(Dealer dealer, Deck deck, Players players) {
        drawAtFirst(dealer, players, deck);
        OutputView.printParticipantsCardAtFirst(dealer, players);
        askPlayersToHit(players, deck);
        drawMoreCardToDealer(dealer, deck);
    }

    private void drawAtFirst(Dealer dealer, Players players, Deck deck) {
        dealer.drawAtFirst(deck);
        players.drawAtFirst(deck);
    }

    private void askPlayersToHit(Players players, Deck deck) {
        players.getPlayers()
                .forEach(player -> askHit(player, deck));
    }

    private void drawMoreCardToDealer(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void askHit(Player player, Deck deck) {
        String doesPlayerWantMoreCard;

        do {
            OutputView.printHitGuideMessage(player);
            doesPlayerWantMoreCard = InputView.getHitValue();
            draw(player, doesPlayerWantMoreCard, deck);
        } while (player.isNotBust() && doesPlayerWantMoreCard.equals(YES));
    }

    private void draw(Player player, String doesPlayerWantMoreCard, Deck deck) {
        if (doesPlayerWantMoreCard.equals(YES)) {
            player.hit(deck.pop());
            OutputView.printPlayerCards(player);
        }
    }

    private void showResult(Dealer dealer, Players players) {
        OutputView.printCardsAndScore(dealer, players);
        Map<Player, MatchResultType> result = calculateMatchResult(dealer, players);
        List<Integer> matchResultCount = countMatchResultType(result);
        OutputView.printMatchTypeResult(matchResultCount, result);
    }

    private Map<Player, MatchResultType> calculateMatchResult(Dealer dealer, Players players) {
        Map<Player, MatchResultType> result = new LinkedHashMap<>();
        players.getPlayers()
                .forEach(player -> result.put(player, dealer.compare(player)));
        return result;
    }

    private List<Integer> countMatchResultType(Map<Player, MatchResultType> result) {
        List<Integer> matchResultCount = new ArrayList<>();
        Arrays.stream(MatchResultType.values())
                .forEach(matchResultType -> matchResultCount.add(Collections.frequency(result.values(), matchResultType)));
        return matchResultCount;
    }
}
