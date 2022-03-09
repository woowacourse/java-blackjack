package fuelinjection;

import fuelinjection.view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        Distance distance = initializeDistance(inputView);

        Cars cars = Cars.ofAllCars();
        cars.injectFuel(distance);
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
