package com.wyl.test.controller;

import com.wyl.test.constants.Gender;
import com.wyl.test.dto.BaseResponse;
import com.wyl.test.entity.User;
import com.wyl.test.facade.IUserFacade;
import com.wyl.test.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 12:04
 * @Description:
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    @Qualifier("userFacadeImpl")
    private IUserFacade iUserFacade;

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public BaseResponse<Page<User>> page(@RequestParam(value = "pageNo") Integer pageNo,
                                         @RequestParam(value = "pageSize") Integer pageSize,
                                         @RequestParam(value = "gender") Gender gender) {
        Page<User> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("gender", gender);
        page.setParams(queryParam);
        Page result = this.iUserFacade.page(page);
        BaseResponse<Page<User>> baseResponse = new BaseResponse<>(result);
        return baseResponse;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BaseResponse get(@PathVariable(value = "id") Long id) {
        User user = iUserFacade.getById(id);
        if(Objects.nonNull(user)) {
            return new BaseResponse(user);
        }
        return new BaseResponse(-1000, "用户不存在");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse add(@RequestBody User user) {
        if(Objects.isNull(user)) {
            return new BaseResponse(-1001, "新增用户信息时参数不能为空");
        }
        User result = this.iUserFacade.add(user);
        if(Objects.nonNull(result)) {
            return new BaseResponse(result);
        }
        return new BaseResponse(-1002, "新增用户信息");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public BaseResponse update(@PathVariable(value = "id") Long id,
                       @RequestBody User user) {
        user.setId(id);
        User result = this.iUserFacade.update(user);
        return new BaseResponse(result);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable(value = "id") Long id) {
        this.iUserFacade.delete(id);
        return new BaseResponse("删除用户信息成功");
    }

}
