package ru.rgs.csvparser.client.clientFallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import ru.rgs.csvparser.client.ServiceFeignClient;
import ru.rgs.csvparser.entity.dto.ClientRequestDto;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;

@Component
public class ServiceFeignClientFallbackFactory implements FallbackFactory<ServiceFeignClient> {
    @Override
    public ServiceFeignClient create(Throwable cause) {
        return clientRequestDto -> new ClientResponseDto("FAILED", "0");
    }
}
