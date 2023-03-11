package com.yiren.test;

import lombok.Data;

import java.util.List;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/3/10 20:09
 * desc     :
 */
@Data
public class User {
    String name;
    Integer id;
    String description;

    List<Integer> scores;

}
