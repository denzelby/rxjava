package composingObservables;

import model.Beer;
import model.Drink;
import model.SoftDrink;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class ObservableDrinks {

    private static List<Drink> loadBeers() {
        List<Drink> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        return beerStock;
    }


    private static List<Drink> loadSoftDrinks() {
        List<Drink> softStock = new ArrayList<>();

        softStock.add(new SoftDrink("Lemonade", "Ukraine", 1.00f));
        softStock.add(new SoftDrink("Pepsi", "USA", 2.00f));
        softStock.add(new SoftDrink("Fanta", "USA", 3.00f));
        return softStock;
    }


    private static Observable<List<Drink>> getDrinks() {

        return Observable.create(subscriber -> {

            subscriber.onNext(loadBeers());   // push the beers pallet

            subscriber.onNext(loadSoftDrinks()); // push the soft drink pallet

            subscriber.onCompleted();
        });
    }

    public static void main(String[] args) {

        Observable<List<Drink>> pallets = getDrinks();
        pallets
                .flatMap(Observable::from)
                .subscribe(
                        data -> System.out.println("Subscriber received " + data),
                        System.err::println,
                        () -> System.out.println("*** The stream is over ***")
                );
    }
}
