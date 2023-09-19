package com.JavaTask.TaskManageMent.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto{
    private String fullName;
    private String userName;
    private String userPhnNumber;
    private String userAddress;
    private String password;
}
