package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Rental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Getter
@NoArgsConstructor
public class RentalRequestDto {
    private String identity;
    private String bikeNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long price;

    public long calculatePrice(){
        this.price = ChronoUnit.DAYS.between(this.startDate,this.endDate) * 5000L;
        return this.price;
    }

    public Rental toEntity(){
        return Rental.builder()
                .identity(this.identity)
                .bikeNum(this.bikeNum)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .build();
    }
}
