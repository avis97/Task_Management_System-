package com.JavaTask.TaskManageMent.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private int userId;
    private String fullName;
    private String userName;
    private String userPhnNumber;
    private String userAddress;
}
