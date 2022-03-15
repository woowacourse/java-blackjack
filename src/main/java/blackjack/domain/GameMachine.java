package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomGenerator;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class GameMachine {

    private final Deck deck;
    private final Players players;

    public GameMachine(final List<String> names, final HashMap<String, Bet> bets) {
        validationNames(names);
        validationbets(bets);
        this.deck = new Deck(new RandomGenerator());
        this.players = new Players(createParticipants(names, bets), createDealer());
    }

    private void validationNames(final List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여자의 이름을 입력해주세요.");
        }
    }

    private void validationbets(HashMap<String, Bet> bets) {
        if (bets == null || bets.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 잘못된 배팅 입력입니다.");
        }
    }

    public List<Participant> createParticipants(final List<String> names, final HashMap<String, Bet> bets) {
        return names.stream()
                .map(name -> new Participant(Cards.createInitCards(deck), name, bets.get(name)))
                .collect(Collectors.toList());
    }

    private Dealer createDealer() {
        return new Dealer(Cards.createInitCards(deck));
    }

    public Card playDraw() {
        return deck.draw();
    }

    public boolean isDealerGetAdditionalCard() {
        if (players.getDealer().acceptableCard()) {
            players.addDealerCard(deck.draw());
            return true;
        }
        return false;
    }

    public Players getPlayers() {
        return this.players;
    }

    public List<Participant> getParicipants() {
        return players.getParticipants();
    }
}
