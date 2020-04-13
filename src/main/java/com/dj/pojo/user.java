package com.dj.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author joker_dj
 * @create 2020-04-13日 17:56
 */
@ApiModel("用户实体类")
public class user {
    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
