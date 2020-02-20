package ru.rgs.csvparser.client.client_fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import ru.rgs.csvparser.client.ServiceFeignClient;
import ru.rgs.csvparser.entity.dto.ClientResponseDto;
import ru.rgs.csvparser.entity.enums.ResponseStatus;

@Component
public class ServiceFeignClientFallbackFactory implements FallbackFactory<ServiceFeignClient> {
    @Override
    public ServiceFeignClient create(Throwable cause) {
        return clientRequestDto -> new ClientResponseDto(ResponseStatus.FAILED, "0");
    }
}
