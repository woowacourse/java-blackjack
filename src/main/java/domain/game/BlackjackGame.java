package domain.game;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.player.Participant;
import domain.player.Players;
import domain.player.info.ParticipantInfo;
import util.HitOrStay;
import util.Notice;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public final class BlackjackGame {

    private final Players players;
    private final Deck deck;

    public BlackjackGame(final Players players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame from(final List<String> participantNames, final Function<String, Integer> function, final CardGenerator cardGenerator) {
        final List<Participant> participants = participantNames.stream()
                .map(ParticipantInfo.ParticipantBuilder::new)
                .map(playerInfoBuilder -> playerInfoBuilder.setBetAmount(function.apply(playerInfoBuilder.getName())))
                .map(ParticipantInfo.ParticipantBuilder::build)
                .map(Participant::of)
                .collect(toList());

        return new BlackjackGame(Players.of(participants), Deck.from(cardGenerator));
    }

    public void drawCards(BiConsumer<Card, List<Participant>> print) {
        players.drawCards(deck);
        print.accept(players.getDealer().showCard(), players.getParticipants());
    }

    public void playTurn(HitOrStay hitOrStay, Notice<Participant> participantNotice, Notice<Boolean> delaerNotice) {
        players.playParticipantsTurn(deck, hitOrStay, participantNotice, delaerNotice);
    }

    public List<String> getParticipantNames() {
        return players.getParticipants().stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public void get(Notice<Integer> dealerNotice, BiConsumer<String, Integer> participantNotice) {
        final ProfitCalculator profitCalculator = ProfitCalculator.of(players.getParticipants(), players.getDealer());

        dealerNotice.print(profitCalculator.getDealerProfit());
        getParticipantNames().forEach(participantName -> participantNotice.accept(participantName, profitCalculator.getProfit(participantName)));
    }
}
