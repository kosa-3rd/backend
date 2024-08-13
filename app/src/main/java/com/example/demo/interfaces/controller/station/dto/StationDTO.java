package com.example.demo.interfaces.controller.station.dto;

import jdk.jfr.Unsigned;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class StationDTO {
    public record SubwayApiResponse(
            ErrorMessage errorMessage,
            List<RealtimeArrival> realtimeArrivalList
    ) {
        public SubwayApiResponse(SubwayApiResponse data) {
            this(data.errorMessage(), data.realtimeArrivalList());
        }
    }

    public record ErrorMessage(
            int status,
            String code,
            String message,
            String link,
            String developerMessage,
            String total
    ) {}

    public record RealtimeArrival(
            String subwayId,
            String updnLine,
            String trainLineNm,
            String statnFid,
            String statnTid,
            String statnId,
            String statnNm,
            String trnsitCo,
            String ordkey,
            String subwayList,
            String statnList,
            String btrainSttus,
            String barvlDt,
            String btrainNo,
            String bstatnId,
            String bstatnNm,
            String recptnDt,
            String arvlMsg2,
            String arvlMsg3,
            String arvlCd,
            String lstcarAt
    ) {}
}
