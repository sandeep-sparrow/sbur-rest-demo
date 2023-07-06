package com.thehecklers.planefinder;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@AllArgsConstructor
public class PositionReporter {

    private final PlaneFinderService pfService;

    @Bean
    Supplier<Iterable<Aircraft>> reportPositions(){
        return () -> {
            try {
                return pfService.getAircraft();
            }catch (IOException e){
                e.printStackTrace();
            }
            return List.of();
        };
    }
}
