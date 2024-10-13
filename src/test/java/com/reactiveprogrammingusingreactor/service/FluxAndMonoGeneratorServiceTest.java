package com.reactiveprogrammingusingreactor.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void nameFlux() {
        //given
        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFLux();
        //then
        StepVerifier.create(namesFlux).expectNext("okan", "ozan", "hakan")
                .verifyComplete();

    }

    @Test
    void nameFluxError() {
        //given
        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFLux();
        //then
        StepVerifier.create(namesFlux)
                .expectNext("ozan", "okan", "hakan")
                .verifyComplete();

    }

    @Test
    void nameFluxCount() {
        //given
        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFLux();
        //then
        StepVerifier.create(namesFlux)
                .expectNextCount(2)
                .verifyComplete();

    }


    @Test
    void namesFLux_map() {
      var namesFlux = fluxAndMonoGeneratorService.namesFLux_map();
        StepVerifier.create(namesFlux)
                .expectNext("OKAN","MERVE")
                .verifyComplete();
    }

    @Test
    void immutability() {
        var namesFlux = fluxAndMonoGeneratorService.namesFLux_immutable();
        StepVerifier.create(namesFlux)
                .expectNext("OKAN","MERVE")
                .verifyComplete();
    }

    @Test
    void namesFLux_filter() {
        int length = 2 ;
        var namesFlux = fluxAndMonoGeneratorService.namesFLux_filter(length);
        StepVerifier.create(namesFlux).expectNext("OKAN","MERVE")
                .verifyComplete();
    }

    @Test
    void namesFLux_flatMap() {
        int length = 2;
        var namesFlux = fluxAndMonoGeneratorService.namesFLux_flatMap(length);
        StepVerifier.create(namesFlux).expectNext("O","K","A","N","M","E","R","V","E")
                .verifyComplete();
    }

    @Test
    void namesFLux_flatmap_async() {
        int length = 2 ;
        var namesFlux = fluxAndMonoGeneratorService.namesFLux_flatmap_async(length);
        StepVerifier.create(namesFlux)
                .expectNextCount(9)
                .verifyComplete();

    }

    @Test
    void namesFLux_concatMap() {
        int length = 2;
        var namesFlux = fluxAndMonoGeneratorService.namesFLux_concatMap(length);
        StepVerifier.create(namesFlux).expectNext("O","K","A","N","M","E","R","V","E")
                .verifyComplete();
    }

    @Test
    void namesMono_flatMap() {
        int length = 2;
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatMap(length);
        StepVerifier.create(namesFlux).expectNext(List.of("O","K","A","N"))
                .verifyComplete();
    }

    @Test
    void namesMono_flatMapMany() {
        int length = 2;
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatMapMany(length);
        StepVerifier.create(namesFlux).expectNext("O","K","A","N")
                .verifyComplete();
    }

    @Test
    void namesFlux_transform() {
        int length = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_transform(length);
        StepVerifier.create(namesFlux).expectNext("O","K","A","N","M","E","R","V","E")
                .verifyComplete();
    }

    @Test
    void explore_concat() {
        var namesFlux = fluxAndMonoGeneratorService.explore_concat();
        StepVerifier.create(namesFlux).expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }
    @Test
    void explore_concatwith() {
        var namesFlux = fluxAndMonoGeneratorService.explore_concatwith();
        StepVerifier.create(namesFlux).expectNext("A","B","C","D","E","F")
                .verifyComplete();
    }

    @Test
    void mono_explore_concatwith() {
        var namesFlux = fluxAndMonoGeneratorService.mono_explore_concatwith();
        StepVerifier.create(namesFlux).expectNext("A","D")
                .verifyComplete();
    }

    @Test
    void explore_merge() {
        var namesFlux = fluxAndMonoGeneratorService.explore_merge();
        StepVerifier.create(namesFlux)
                .expectNext("A","D","B","E","C","F")
                .verifyComplete();
    }

    @Test
    void explore_mergeWtih_mono() {
        var namesFlux = fluxAndMonoGeneratorService.explore_mergeWtih_mono();
        StepVerifier.create(namesFlux)
                .expectNext("A","D")
                .verifyComplete();
    }
}