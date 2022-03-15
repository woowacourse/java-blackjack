package fuelinjection;

import fuelinjection.view.InputView;
import fuelinjection.view.ResultView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        Distance distance = initializeDistance(inputView);

        Cars cars = Cars.ofAllCars();
        cars.injectFuel(distance);
        new ResultView().printFuelMap(cars.mapFuel());
    }

    private static Distance initializeDistance(InputView inputView) {
        try {
            return new Distance(inputView.askDistance());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initializeDistance(inputView);
        }
    }
}
