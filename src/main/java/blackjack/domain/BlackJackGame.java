package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Player dealer;
    private final List<Player> gamers;

    public BlackJackGame(final Player dealer, final List<Player> gamers) {
        checkDuplicateName(gamers);
        this.dealer = dealer;
        this.gamers = gamers;
    }

    private void checkDuplicateName(final List<Player> gamers) {
        Set<Player> tempSet = new HashSet<>(gamers);
        if (gamers.size() != tempSet.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
        }
    }

    public void giveFirstCards(final Deck deck) {
        giveTwoCards(dealer, deck);

        for (Player gamer : gamers) {
            giveTwoCards(gamer, deck);
        }
    }

    private void giveTwoCards(final Player player, final Deck deck) {
        for (int i = 0; i < 2; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Player, Result> calculateResultBoard() {
        int dealerResult = dealer.calculateResult();
        return gamers.stream()
                .collect(Collectors.toMap(gamer -> gamer,
                        gamer -> Result.findResult(dealerResult,
                                gamer.calculateResult()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamers() {
        return List.copyOf(gamers);
    }
}
