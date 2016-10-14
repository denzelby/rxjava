/**
 * Created by yfain11 on 4/19/16.
 */

import model.Beer;
import model.BeerStocks;
import rx.Observable;

import java.util.List;

public class BeerServerWithFailover {

    public static Observable<Beer> getData() {

        List<Beer> beerStock = BeerStocks.create();
        System.out.println("*** Getting beers from the main data source ***");

        // Create an observable passing subscriber (implements Observer)
        // provided by the client
        return Observable.create(subscriber -> {
            try {
                for (Beer beer : beerStock) {
                    subscriber.onNext(beer);

                    Thread.sleep(500); // Emulating delay in getting data

                    if (Math.random() < 0.3) {  // Emulating data error
                        throw new Throwable("Some stupid error");
                    }
                }
            } catch (Throwable err) {
                subscriber.onError(new Throwable("Error in getting beer info"));
            }

            subscriber.onCompleted();
        });
    }

    public static Observable<Beer> getDataFromAnotherServer() {

        System.out.println("*** Getting beers from the ALTERNATIVE data source ***");

        List<Beer> beerStock = BeerStocks.create();
        return Observable.create(subscriber -> {
            try {
                for (Beer beer : beerStock) {
                    subscriber.onNext(beer);
                    Thread.sleep(1000); // Emulating delay in getting data
                }
            } catch (Throwable err) {
                subscriber.onError(new Throwable("Error in getting beer info"));
            }
            subscriber.onCompleted();
        });
    }
}

