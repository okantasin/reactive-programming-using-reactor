package com.reactiveprogrammingusingreactor.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    Flux<String> fluxList = Flux.fromIterable(List.of("okan", "merve"));
    Mono<String> mono = Mono.just("okan");

    public Mono<String> nameMono(Integer id) {
        return mono;
    }
    public Flux<String> namesFLux() {
        return fluxList.log();
    }
    public Flux<String> namesFLux_map() {
        return fluxList.map(String::toUpperCase).log();
    }
    public Flux<String> namesFLux_immutable() {
        return fluxList.map(String::toUpperCase);
    }
    public Flux<String> namesFLux_filter(int length) {
        return fluxList.map(String::toUpperCase).filter(s -> s.length() > length).log();
    }
    public Flux<String> namesFLux_flatMap(int length) {
        return fluxList
                .map(String::toUpperCase)
                .filter(s -> s.length() > length)
                .flatMap(this::splintString)
                .log();
    }
    public Flux<String> namesFLux_flatmap_async(int length) {
        return fluxList
                .map(String::toUpperCase)
                .filter(s -> s.length() > length)
                .flatMap(this::withDelay)
                .log();
    }
    public Flux<String> namesFLux_concatMap(int length) {
        return fluxList
                .map(String::toUpperCase)
                .filter(s -> s.length() > length)
                .concatMap(this::withDelay)
                .log();
    }
    public Mono<String> namesMono_map_filter(Integer id) {
        return mono.map(String::toUpperCase).filter(s -> s.length() > id).log();
    }
    public Mono<List<String>> namesMono_flatMap(Integer id) {
        return mono.map(String::toUpperCase)
                .filter(s -> s.length() > id)
                .flatMap(this::splintStringMono);
    }
    private Mono<List<String>> splintStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray);
        return Mono.just(charList);
    }
    public Flux<String> namesMono_flatMapMany(Integer id) {
        return mono.map(String::toUpperCase)
                .filter(s -> s.length() > id)
                .flatMapMany(this::splintString).log();
    }
    public Flux<String> namesFlux_transform(Integer length) {

        Function<Flux<String>, Flux<String>> filterMap =
                name -> name.map(String::toUpperCase)
                        .filter(s -> s.length() > length);
        return fluxList
                .transform(filterMap)
                .map(String::toUpperCase)
                .flatMap(this::splintString)
                .log();
    }
    public Flux<String> explore_concat(){
        var abcFlux = Flux.just("A","B","C");
        var defFlux = Flux.just("D","E","F");
        return Flux.concat(abcFlux, defFlux).log();

    }
    public Flux<String> explore_concatwith(){
        var aMono = Flux.just("A","B","C");
        var bMono = Flux.just("D","E","F");
        return aMono.concatWith(bMono).log();

    }
    public Flux<String> mono_explore_concatwith(){
        var abcFlux = Mono.just("A");
        var defFlux = Mono.just("D");
        return abcFlux.concatWith(defFlux).log();

    }
    public Flux<String> splintString (String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }
    public Flux<String> withDelay (String name){
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }
    public Flux<String> explore_merge(){
        var abcFlux = Flux.just("A","B","C")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("D","E","F")
                .delayElements(Duration.ofMillis(90));
        return Flux.merge(abcFlux, defFlux).log();

    }
    public Flux<String> explore_mergeWtih_mono(){
        var aMono = Mono.just("A");
        var dMono = Mono.just("D");
        return aMono.mergeWith(dMono).log();

    }

}
