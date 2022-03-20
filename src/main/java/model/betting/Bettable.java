package model.betting;

import model.Status;

public interface Bettable {
    long calculateBetting(Status status);
}
