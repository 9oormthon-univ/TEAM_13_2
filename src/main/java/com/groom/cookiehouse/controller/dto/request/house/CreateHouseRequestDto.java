package com.groom.cookiehouse.controller.dto.request.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateHouseRequestDto {

    @NotNull
    private Long icingId;

    @NotEmpty
    private List<Long> cookieIds;

    @NotBlank
    private String houseName;

}
