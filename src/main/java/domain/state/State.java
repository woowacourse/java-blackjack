package domain;

public interface State {
    State hit(Card card);

    State stand();
}
