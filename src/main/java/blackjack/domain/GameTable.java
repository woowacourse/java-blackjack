package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;

public class GameTable {
    private static final String YES = "Y";

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public GameTable(List<Player> players) {
        deck = new Deck(Card.values());
        this.dealer = new Dealer();
        this.players = players;
    }

    public void playGame() {
        start();
        play();
        finish();
    }

    private void start() {
        drawAtFirst();
        OutputView.printShowUsersCardMessage(players);
        OutputView.showCards(dealer, players);
    }

    private void drawAtFirst() {
        players.forEach(player -> {
            player.hit(deck.pop());
            player.hit(deck.pop());
        });
        dealer.hit(deck.pop());
        dealer.hit(deck.pop());
    }

    private void play() {
        players.forEach(this::askHit);
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void askHit(Player player) {
        String doesPlayerWantMoreCard;

        do {
            OutputView.printHitGuideMessage(player);
            doesPlayerWantMoreCard = InputView.getHitValue();
            draw(player, doesPlayerWantMoreCard);
        } while (player.isNotBust() && doesPlayerWantMoreCard.equals(YES));
    }

    private void draw(Player player, String doesPlayerWantMoreCard) {
        if (doesPlayerWantMoreCard.equals(YES)) {
            player.hit(deck.pop());
            OutputView.printPlayerCards(player);
        }
    }

    private void finish() {
        OutputView.printCardsAndScore(dealer, players);
        Map<Player, MatchResultType> result = getMatchResult();
        List<Integer> matchResultCount = getMatchResultCount(result);
        OutputView.printResult(matchResultCount, result);
    }

    private Map<Player, MatchResultType> getMatchResult() {
        Map<Player, MatchResultType> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, dealer.compare(player)));
        return result;
    }

    private List<Integer> getMatchResultCount(Map<Player, MatchResultType> result) {
        List<Integer> matchResultCount = new ArrayList<>();
        Arrays.stream(MatchResultType.values())
                .forEach(matchResultType -> matchResultCount.add(Collections.frequency(result.values(), matchResultType)));
        return matchResultCount;
    }
}
