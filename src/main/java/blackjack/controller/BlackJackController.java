package blackjack.controller;

import blackjack.domain.MatchResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;

public class BlackJackController {
    private static final String YES = "Y";

    public void run() {
        OutputView.printPlayerNameInputGuideMessage();
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, InputView.getPlayerNameInput());
        Deck deck = new Deck(Card.values());
        drawAtFirst(participants, deck);
        OutputView.printParticipantsCard(participants);
        askPlayersToHit(participants, deck);
        dealerToHit(dealer, deck);
        OutputView.printCardsAndScore(participants);
        finish(dealer, participants);
    }

    private void dealerToHit(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.hit(deck.pop());
            OutputView.printDealerHitMessage();
        }
    }

    private void drawAtFirst(Participants participants, Deck deck) {
        participants.drawAtFirst(deck);
    }

    private void askPlayersToHit(Participants participants, Deck deck) {
        participants.getParticipant().stream()
                .filter(participant -> participant instanceof Player)
                .forEach(player -> askHit((Player) player, deck));
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

    private void finish(Dealer dealer, Participants participants) {
        Map<Player, MatchResultType> result = getMatchResult(dealer, participants);
        List<Integer> matchResultCount = getMatchResultCount(result);
        OutputView.printResult(matchResultCount, result);
    }

    private Map<Player, MatchResultType> getMatchResult(Dealer dealer, Participants participants) {
        Map<Player, MatchResultType> result = new LinkedHashMap<>();
        participants.getParticipant().stream()
                .filter(participant -> participant instanceof Player)
                .forEach(player -> result.put((Player) player, dealer.compare((Player) player)));
        return result;
    }

    private List<Integer> getMatchResultCount(Map<Player, MatchResultType> result) {
        List<Integer> matchResultCount = new ArrayList<>();
        Arrays.stream(MatchResultType.values())
                .forEach(matchResultType -> matchResultCount.add(Collections.frequency(result.values(), matchResultType)));
        return matchResultCount;
    }
}
