package config;

import domain.card.CardDeckInitializer;
import domain.card.DefaultCardDeckInitializer;
import view.ApplicationView;
import view.InputReader;
import view.OutputWriter;

public record BlackjackGameConfiguration(ApplicationView view, CardDeckInitializer gameCardDeckInitializer) {

    public BlackjackGameConfiguration() {
        this(ApplicationView.of(new InputReader(), new OutputWriter()), new DefaultCardDeckInitializer());
    }
}
