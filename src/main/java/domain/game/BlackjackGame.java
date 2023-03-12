package domain.game;

import DTO.*;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.player.Participant;
import domain.player.Players;
import domain.player.info.PlayerInfo;
import util.HitOrStay;
import util.Notice;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public final class BlackjackGame {

    private final Players players;
    private final Deck deck;

    public BlackjackGame(final Players players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame from(final List<String> participantNames, final Function<String, Integer> function, final CardGenerator cardGenerator) {
        final List<Participant> participants = participantNames.stream()
                .map(PlayerInfo.PlayerInfoBuilder::new)
                .map(playerInfoBuilder -> playerInfoBuilder.setBetAmount(function.apply(playerInfoBuilder.getName())))
                .map(PlayerInfo.PlayerInfoBuilder::build)
                .map(Participant::of)
                .collect(toList());

        return new BlackjackGame(Players.of(participants), Deck.from(cardGenerator));
    }

    public void drawCards() {
        players.drawCards(deck);
    }

    public void playParticipantsTurn(HitOrStay hitOrStay, Notice<Participant> notice) {
        players.playParticipantsTurn(deck, hitOrStay, notice);
    }

    public void playDealerTurn(Notice<Boolean> notice) {
        players.playDealerTurn(deck, notice);
    }

    public GameResult judgeResult() {
        return GameResult.of(players.getDealer(), players.getParticipants());
    }

    public List<ParticipantDTO> getParticipantDTOs() {
        return DTOFactory.createParticipantDTOs(players.getParticipants());
    }

    public DealerDTO getDealerDTO() {
        return DTOFactory.createDealerDTO(players.getDealer());
    }

    public DealerResultDto getDealerResultDTO() {
        return DTOFactory.createDealerResultDTO(getFinalResultDTO());
    }

    public List<FinalResultDTO> getFinalResultDTO() {
        return DTOFactory.createFinalResultDTOs(judgeResult());
    }
}
