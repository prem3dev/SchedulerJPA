package com.example.schedulerjpa.dto.scheduledto;

import com.example.schedulerjpa.customannotation.NotAllNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NotAllNull
@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {

    private final String title;

    private final String task;
}
