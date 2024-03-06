package domain;

import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int BLACKJACK_SCORE = 21;

    private final Participant participant;
    private final Cards cards;

    public Game(final Dealer dealer, final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        this.participant = new Participant(dealer, players);
        cards = new Cards();
    }

    public Game(final Participant participant, final Cards cards) {
        this.participant = participant;
        this.cards = cards;
    }

    public CardsStatus initiateGameCondition() {
        List<CardStatus> status = new ArrayList<>();
        Dealer dealer = participant.dealer();
        status.add(new CardStatus(dealer.getName(), pickTwoCards(dealer)));
        for (Player player : participant.players()) {
            status.add(new CardStatus(player.getName(), pickTwoCards(player)));
        }
        return new CardsStatus(status);
    }

    public boolean isNotDealerBlackJack() {
        Dealer dealer = participant.dealer();
        return !dealer.isBlackJack(BLACKJACK_SCORE);
    }

    private List<Card> pickTwoCards(final Player player) {
        player.saveCard(cards.pick());
        player.saveCard(cards.pick());
        return player.getCards();
    }

    public CardStatus pickOneCard(final String name) {
        List<Player> players = participant.players();
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        foundPlayer.saveCard(cards.pick());
        return new CardStatus(foundPlayer.getName(), foundPlayer.getCards());
    }


    public List<String> getPlayerNames() {
        List<Player> players = participant.players();
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public CardStatus getCurrentCardStatus(final String name) {
        List<Player> players = participant.players();
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        return new CardStatus(foundPlayer.getName(), foundPlayer.getCards());
    }

    public int giveCardsToDealer() {
        Dealer dealer = participant.dealer();
        int threshold = 16;
        int currentScore = dealer.calculateScore();

        int count = 0;
        while (currentScore <= threshold) {
            dealer.saveCard(cards.pick());
            currentScore = dealer.calculateScore();
            count++;
        }
        return count;
    }

    public Participant getParticipant() {
        return participant;
    }
}
