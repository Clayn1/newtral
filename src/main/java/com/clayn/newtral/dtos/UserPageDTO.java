package com.clayn.newtral.dtos;

import com.clayn.newtral.model.UserPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPageDTO {
    private List<UserPage> userPages;
    private int totalElements;
    private int pagesCount;
    private boolean isLast;
    private boolean isEmpty;
}
