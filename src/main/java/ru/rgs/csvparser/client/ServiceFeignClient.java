package ru.rgs.csvparser.client;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rgs.csvparser.entity.dto.ClientRequestDto;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;

@FeignClient(name = "client-score-client", url = "http://localhost:8090")
public interface ClientFeignClient {

    @PostMapping("/score")
    ClientResponseDto getClientScore(@RequestBody ClientRequestDto clientRequestDto);
}
