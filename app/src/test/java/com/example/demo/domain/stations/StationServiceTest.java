package com.example.demo.domain.stations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StationServiceTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Value("${STATION_KEY}")
    private String stationKey;

    @Test
    void testGetStationData() {
        String uri = "http://swopenapi.seoul.go.kr/api/subway/{key}/json/realtimeStationArrival/1/6/강남";
        // 1호선 -> 1001
        // 1호선 요청에따라서 해당되는 역들
        // 1호선 -> 서울역, 시청, 종각, 종로3가, 종로5가, 동대문, 신설동, 제기동, 청량리, 동묘앞, 시청, 서울역
        //List<1stLine> = {서울역, 시청, 종각, 종로3가, 종로5가, 동대문, 신설동, 제기동, 청량리, 동묘앞, 시청, 서울역}
        // for( 1stLine line : 1stLine){ line.getStationName(); }
        // re
        String result = restTemplate.getForObject(uri, String.class, stationKey);

        assertNotNull(result);
        System.out.println(result);
        // 여기에 추가적인 결과 검증 로직...
    }
}