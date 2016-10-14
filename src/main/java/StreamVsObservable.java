/**
 * Created by yfain11 on 4/9/16.
 */

import model.BeerStocks;
import rx.Observable;

public class StreamVsObservable {

    public static void main(String[] args) {

        // === Java 8 Stream
        System.out.println("\n== Iterating over Java 8 Stream");

        BeerStocks.create().stream()
                .skip(1)
                .limit(3)
                .filter(b -> "USA".equals(b.getCountry()))
                .map(b -> b.getName() + ": $" + b.getPrice())
                .forEach(System.out::println);

        // === RxJava Observable

        System.out.println("\n== Subscribing to Observable ");

        Observable
                .from(BeerStocks.create())
                .skip(1)
                .take(3)
                .filter(b -> "USA".equals(b.getCountry()))
                .map(b -> b.getName() + ": $" + b.getPrice())
                .subscribe(
                        System.out::println,
                        System.out::println,
                        () -> System.out.println("Streaming is complete")
                );
    }
}
