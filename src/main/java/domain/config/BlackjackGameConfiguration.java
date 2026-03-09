package domain.config;

import domain.card.CardDeckInitializer;
import domain.card.DefaultCardDeckInitializer;
import domain.view.ApplicationView;
import domain.view.InputReader;
import domain.view.OutputWriter;

public record BlackjackGameConfiguration(ApplicationView view, CardDeckInitializer gameCardDeckInitializer) {

    public BlackjackGameConfiguration() {
        this(ApplicationView.of(new InputReader(), new OutputWriter()), new DefaultCardDeckInitializer());
    }
}
