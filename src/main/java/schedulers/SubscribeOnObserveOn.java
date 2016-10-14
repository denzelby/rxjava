package schedulers;

import model.Beer;
import model.BeerStocks;
import rx.Observable;
import rx.schedulers.Schedulers;

public class SubscribeOnObserveOn {

    public static void main(String[] args) {

        Observable.from(BeerStocks.create())
                .subscribeOn(Schedulers.computation())  // push data on computation thread
                .doOnNext(SubscribeOnObserveOn::log)    // Side effect: Log on computation thread
                .observeOn(Schedulers.io())             // Process on another io thread
                .subscribe(SubscribeOnObserveOn::matureBeer);

        // Sleep just to keep the program running
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void matureBeer(Beer beer) {
        try {
            System.out.println("** Maturing " + beer.getName() +
                    " on " + Thread.currentThread().getName());

            Thread.sleep((int) (Math.random() * 500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void log(Beer beer) {
        System.out.println("===> Logging " + beer.getName() +
                " on " + Thread.currentThread().getName());
    }
}
