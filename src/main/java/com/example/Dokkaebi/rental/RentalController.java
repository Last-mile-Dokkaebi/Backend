package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.rental.dto.RentalHisResDto;
import com.example.Dokkaebi.rental.dto.RentalRequestDto;
import com.example.Dokkaebi.rental.dto.RentalResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final MemberService memberService;

    @PostMapping("/rental/price")
    public long rentalPrice(@RequestBody RentalRequestDto rentalRequestDto) {
        return rentalRequestDto.calculatePrice();
    }

    @PostMapping("/rental/new")
    public void startRental(@RequestHeader(value = "Authorization") @NotNull String accessToken, @RequestBody RentalRequestDto rentalRequestDto) {
        Member member = memberService.findMember(accessToken);
        rentalService.startRental(member, rentalRequestDto);
    }

    @ApiOperation(value = "킥보드 렌탈기록")
    @GetMapping("/rental")
    public RentalHisResDto checkRental(@RequestHeader(value = "Authorization") String accessToken) {
        return rentalService.findAllRentalByMember(accessToken);
    }

    @ApiOperation(value = "킥보드 주행기록")
    @GetMapping("/rental/{id}")
    public RentalResDto checkRideHistory(@PathVariable Long id) {
        return rentalService.findRentalById(id);
    }

    @ApiOperation(value = "대여한 킥보드 반납")
    @PostMapping("/rental/return")
    public void returnScooter(@RequestHeader(value = "Authorization") String accessToken) {
        rentalService.returnScooter(accessToken);
    }
}
