package ru.rgs.csvparser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.rgs.csvparser.client.client_fallback.ServiceFeignClientFallbackFactory;
import ru.rgs.csvparser.entity.dto.ClientRequestDto;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;

@FeignClient(name = "feign-score-client", url = "http://localhost:8081", fallbackFactory = ServiceFeignClientFallbackFactory.class)
public interface ServiceFeignClient {

    @PostMapping("/score")
    ClientResponseDto getClientScore(@RequestBody ClientRequestDto clientRequestDto);
}
